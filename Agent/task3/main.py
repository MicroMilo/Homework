import os
import json
import asyncio
import autogen
import logging
import config
from typing import Literal, List, Dict, Any, Optional
from dataclasses import dataclass, field, is_dataclass
from enum import Enum

# --- Configuration: Import API_KEY and BASE_URL from config.py ---
# Note: For this Canvas environment, API keys are typically handled by the execution environment.
# You might need to adapt this part if running in a different setup.
try:
    # Simulating config for Canvas environment
    API_KEY = config.API_KEY
    BASE_URL = config.BASE_URL
    if API_KEY == "YOUR_API_KEY_HERE":
        print("Warning: API_KEY not found in environment variables. Please set it.")
except ImportError:
    print("Warning: config.py not found. This is expected in some environments.")
    API_KEY = os.getenv("OPENAI_API_KEY")
    BASE_URL = os.getenv("OPENAI_API_BASE")

if not API_KEY or API_KEY == "YOUR_OPENAI_API_KEY_HERE":
    raise ValueError(
        "API_KEY is not set. Please configure it in your environment."
    )

# --- Define DSL for Agent Output ---

class ElementType(str, Enum):
    """Enumeration for different types of diagram elements."""
    USE_CASE = "use_case"
    SEQUENCE_DIAGRAM = "use_case_diagram"
    CLASS_DIAGRAM = "class_diagram"
    OCL_CONTRACT = "ocl_contract"

@dataclass
class DiagramElement:
    """Represents a single element within the domain model."""
    type: ElementType
    name: str
    content: str

@dataclass
class DomainModel:
    """The collection of all diagram elements forming the domain model."""
    name: str
    elements: List[DiagramElement] = field(default_factory=list)

    def to_json(self) -> str:
        """Serializes the DomainModel to a JSON string."""
        def default_serializer(o):
            if isinstance(o, Enum):
                return o.value
            if is_dataclass(o):
                return o.__dict__
            raise TypeError(f"Object of type {o.__class__.__name__} is not JSON serializable")
        return json.dumps(self, default=default_serializer, indent=4, ensure_ascii=False)

@dataclass
class ModelingOutput:
    """The complete output structure expected from the ModelingAgent."""
    domain_model: DomainModel
    summary: str

    def to_json(self) -> str:
        """Serializes the ModelingOutput to a JSON string."""
        def default_serializer(o):
            if isinstance(o, Enum):
                return o.value
            if is_dataclass(o):
                return o.__dict__
            raise TypeError(f"Object of type {o.__class__.__name__} is not JSON serializable")
        return json.dumps(self, default=default_serializer, indent=4, ensure_ascii=False)

@dataclass
class EvaluationFeedback:
    """The feedback structure expected from the EvaluationAgent."""
    score: Literal["pass", "needs_improvement", "fail"]
    feedback: str

    def to_json(self) -> str:
        """Serializes the EvaluationFeedback to a JSON string."""
        return json.dumps(self, default=lambda o: o.__dict__, indent=4, ensure_ascii=False)

# --- Define AutoGen Configuration List ---
llm_config_list = [
    {
        "model": "deepseek-chat",
        "api_key": API_KEY,
        "base_url": BASE_URL,
        "api_type": "openai",
    }
]

# --- Define System Prompts for AutoGen Agents ---

modeling_agent_instructions = """
你是一位资深的软件需求分析师，精通领域建模。你的任务是根据以下背景信息，为**学校会议室预订管理系统**建立详细的需求模型。

**背景信息：**
* 系统面向用户：学生、教师、管理员
* 会议室资源：多个会议室，每个会议室有容量、设备（如投影仪、麦克风）等属性
* 预订流程：用户可以查询可用会议室，提交预订申请，管理员审批申请
* 预订规则：会议室预订有时间限制、时长限制、冲突检测等规则
* 系统功能：
    * 用户注册/登录
    * 会议室信息展示（包括容量、设备、位置等）
    * 会议室可用性查询（按日期、时间、容量等条件）
    * 预订申请提交（包括会议主题、时间、参会人数等信息）
    * 预订申请审批（管理员功能）
    * 预订信息管理（查看、修改、取消预订）
    * 系统权限管理（不同用户角色有不同权限）
    * 会议室使用情况统计

**输出要求：**

请严格按照以下的JSON格式输出整个需求模型。你的输出应该是一个JSON对象，包含两个顶级键：`domain_model` 和 `summary`。

`domain_model` 键的值应是一个JSON对象，其中包含 `name` 和 `elements`。
`elements` 键的值应是一个JSON数组，每个元素表示一个 `DiagramElement`。

`DiagramElement` 结构如下：
```json
{
  "type": "use_case" | "sequence_diagram" | "class_diagram" | "ocl_contract",
  "name": "元素的具体名称，如'用户注册用例'",
  "content": "对应元素的DSL表示，例如：\\n- 用例图的JSON结构\\n- 系统顺序图的JSON结构\\n- 概念类图的JSON结构\\n- OCL合约的JSON结构"
}
```

请为以下每种类型的元素都生成至少两个例子，并将其内容（`content` 字段）严格按照其各自的DSL格式表示：

1.  **用例图 (ElementType.USE_CASE):**
    ```json
    {
      "系统名称": "学校会议室预订管理系统",
      "用例": [
        {
          "名称": "用户注册",
          "参与者": ["学生", "教师", "管理员"],
          "描述": "允许新用户在系统中创建账户。",
          "包含": [],
          "扩展": []
        },
        {
          "名称": "查询会议室可用性",
          "参与者": ["学生", "教师", "管理员"],
          "描述": "用户可以根据日期、时间、容量和设备等条件查找可用的会议室。",
          "包含": [],
          "扩展": []
        }
      ],
      "参与者": [
        {
          "名称": "学生",
          "用例": ["用户注册", "查询会议室可用性", "提交预订申请", "查看预订信息", "取消预订"]
        },
        {
          "名称": "教师",
          "用例": ["用户注册", "查询会议室可用性", "提交预订申请", "查看预订信息", "取消预订"]
        },
        {
          "名称": "管理员",
          "用例": ["用户注册", "查询会议室可用性", "审批预订申请", "管理会议室信息", "查看系统统计"]
        }
      ]
    }
    ```

2.  **系统顺序图 (ElementType.SEQUENCE_DIAGRAM):**
    ```json
    {
      "名称": "提交预订申请",
      "参与者": "用户",
      "用例": "提交预订申请",
      "消息": [
        {
          "发送者": "用户界面",
          "接收者": "系统",
          "消息名称": "submitBookingRequest",
          "参数": ["会议主题", "会议室ID", "开始时间", "结束时间", "参会人数"]
        },
        {
          "发送者": "系统",
          "接收者": "会议室资源管理模块",
          "消息名称": "checkAvailability",
          "参数": ["会议室ID", "开始时间", "结束时间"]
        }
      ]
    }
    ```

3.  **概念类图 (ElementType.CLASS_DIAGRAM):**
    ```json
    {
      "系统名称": "学校会议室预订管理系统",
      "类": [
        {
          "名称": "用户",
          "属性": ["userId: String", "username: String", "passwordHash: String", "role: UserRole"],
          "关联": ["预订"]
        },
        {
          "名称": "会议室",
          "属性": ["roomId: String", "name: String", "capacity: Integer", "location: String", "equipment: List<String>"],
          "关联": ["预订"]
        }
      ],
      "关系": [
        {
          "类型": "关联",
          "类1": "用户",
          "类2": "预订",
          "描述": "一个用户可以有多个预订",
          "多重度1": "1",
          "多重度2": "*"
        },
        {
          "类型": "关联",
          "类1": "会议室",
          "类2": "预订",
          "描述": "一个会议室可以有多个预订",
          "多重度1": "1",
          "多重度2": "*"
        }
      ]
    }
    ```

4.  **OCL合约 (ElementType.OCL_CONTRACT):**
    ```json
    {
      "服务": "预订服务",
      "操作": "提交预订申请(meetingRoomId: String, startTime: DateTime, endTime: DateTime)",
      "前置条件": "context MeetingRoom::submitBooking(meetingRoomId: String, startTime: DateTime, endTime: DateTime)\\npre: MeetingRoom.allInstances()->exists(r | r.roomId = meetingRoomId) -- 会议室存在\\npre: self.isLoggedIn() -- 用户已登录\\npre: not Booking.allInstances()->exists(b | b.meetingRoom.roomId = meetingRoomId and (b.startTime < endTime and b.endTime > startTime)) -- 无时间冲突",
      "后置条件": "post: Booking.allInstances()->exists(b | b.meetingRoom.roomId = meetingRoomId and b.startTime = startTime and b.endTime = endTime and b.requester = self) -- 成功创建预订",
      "不变式": "context MeetingRoom\\ninv: self.capacity > 0 -- 容量必须大于零"
    }
    ```

请确保输出的JSON结构正确，且 `content` 字段的内部JSON也严格符合其各自的DSL格式，内容完整、准确地反映了上述背景信息。并且整个输出必须是单个JSON对象，可以直接被 `json.loads()` 解析为 `ModelingOutput` 结构。
"""

evaluation_agent_instructions = """
你是一位高度批判的软件架构评审员。你的任务是评估由另一个代理生成的领域模型。

根据以下标准评估模型：

1.  **完整性：** 模型是否涵盖了系统的所有基本方面？
2.  **一致性：** 模型的不同元素是否彼此一致？
3.  **正确性：** 模型是否准确地表示了系统的功能和数据？
4.  **清晰度：** 模型是否易于理解和遵循？
5.  **符合DSL：** 输出是否按照定义的DSL正确格式化？特别是所有 `content` 字段中的嵌套JSON是否有效且符合其对应的DSL定义（用例、顺序图、类图、OCL合约的JSON结构）？

提供“通过”、“需要改进”或“失败”的分数。你的输出必须是只包含 `score` 和 `feedback` 字段的JSON对象。

**示例输出：**
```json
{
  "score": "needs_improvement",
  "feedback": "OCL合约的语法不正确，需要修正。用例描述不够详细。"
}
```

第一次尝试永远不要给出“通过”。
"""

# --- AutoGen Agents Definition ---
modeler = autogen.AssistantAgent(
    name="DomainModeler",
    llm_config={
        "config_list": llm_config_list,
        "temperature": 0.7,
    },
    system_message=modeling_agent_instructions,
)

evaluator_assistant = autogen.AssistantAgent(
    name="ModelEvaluatorAssistant",
    llm_config={
        "config_list": llm_config_list,
        "temperature": 0.3,
    },
    system_message=evaluation_agent_instructions,
)

user_proxy = autogen.UserProxyAgent(
    name="UserProxy",
    human_input_mode="NEVER",
    code_execution_config={"use_docker": False},
    is_termination_msg=lambda x: True,
)

# --- AutoGen Workflow ---
async def main() -> None:
    # --- Configure Logging ---
    logger = logging.getLogger()
    logger.setLevel(logging.INFO)

    if logger.hasHandlers():
        logger.handlers.clear()

    # Create a file handler
    file_handler = logging.FileHandler("autogen_workflow.log", mode='w', encoding='utf-8')
    file_handler.setLevel(logging.INFO)

    # Create a stream handler to print to console
    stream_handler = logging.StreamHandler()
    stream_handler.setLevel(logging.INFO)

    # Create a formatter and set it for both handlers
    formatter = logging.Formatter('%(asctime)s - %(levelname)s - %(message)s')
    file_handler.setFormatter(formatter)
    stream_handler.setFormatter(formatter)

    # Add handlers to the logger
    logger.addHandler(file_handler)
    logger.addHandler(stream_handler)
    # --- Logging Configuration End ---

    logger.info("Starting Domain Modeling Workflow with AutoGen.")

    initial_request = "请为学校会议室预订管理系统生成一份详细的需求模型，包括用例图、系统顺序图、概念类图和OCL合约，并确保严格按照指定的JSON格式输出，并为每种图表至少提供两个示例。"

    latest_model_json: Optional[str] = None
    iteration = 0
    max_iterations = 3

    evaluator_feedback_obj: Optional[EvaluationFeedback] = None

    while iteration < max_iterations:
        iteration += 1
        logger.info(f"\n--- Iteration {iteration}: Generating Model ---")

        modeler_raw_output = None
        evaluator_raw_output = None

        if iteration == 1:
            message_for_modeler = initial_request
        else:
            if latest_model_json and evaluator_feedback_obj and evaluator_feedback_obj.feedback:
                message_for_modeler = (
                    f"这是你上一次生成的模型：\n{latest_model_json}\n\n"
                    f"以下是评审员的反馈，请你根据反馈改进模型：\n{evaluator_feedback_obj.feedback}"
                )
            else:
                logger.warning("Missing previous model or feedback for refinement. Re-sending initial request.")
                message_for_modeler = initial_request

        try:
            modeler_chat_result = user_proxy.initiate_chat(
                recipient=modeler,
                message=message_for_modeler,
                clear_history=True,
                max_turns=1
            )
            
            if modeler_chat_result.chat_history and len(modeler_chat_result.chat_history) > 0:
                modeler_raw_output = modeler_chat_result.chat_history[-1].get("content")
            else:
                logger.error("Modeler returned no messages.")
                break

            if not modeler_raw_output:
                logger.error(f"Modeler returned an empty message in iteration {iteration}.")
                break

            logger.info(f"Modeler Raw Output (Iteration {iteration}):\n{modeler_raw_output}")

            # Extract JSON from markdown code block if present
            json_string_modeler = modeler_raw_output.strip()
            if json_string_modeler.startswith("```json"):
                json_string_modeler = json_string_modeler[len("```json"):].strip()
                if json_string_modeler.endswith("```"):
                    json_string_modeler = json_string_modeler[:-len("```")].strip()

            modeling_output_data = json.loads(json_string_modeler)
            latest_model_output = ModelingOutput(
                domain_model=DomainModel(
                    name=modeling_output_data["domain_model"]["name"],
                    elements=[DiagramElement(**elem_data) for elem_data in modeling_output_data["domain_model"]["elements"]]
                ),
                summary=modeling_output_data["summary"]
            )
            latest_model_json = latest_model_output.to_json()
            logger.info("\n--- Parsed Domain Model ---")
            logger.info(latest_model_json)

        except json.JSONDecodeError:
            logger.exception(f"Modeler's output was not valid JSON in iteration {iteration}. Raw output: {modeler_raw_output}")
            break
        except (KeyError, IndexError):
            logger.exception(f"Missing expected key or empty history in modeler's output in iteration {iteration}. Raw output: {modeler_raw_output}")
            break
        except Exception:
            logger.exception(f"An unexpected error occurred during modeling in iteration {iteration}. Raw output: {modeler_raw_output}")
            break

        logger.info(f"\n--- Iteration {iteration}: Evaluating Model ---")

        try:
            evaluator_chat_result = user_proxy.initiate_chat(
                recipient=evaluator_assistant,
                message=f"请评估以下需求模型JSON：\n{latest_model_json}",
                clear_history=True,
                max_turns=1
            )

            if evaluator_chat_result.chat_history and len(evaluator_chat_result.chat_history) > 0:
                evaluator_raw_output = evaluator_chat_result.chat_history[-1].get("content")
            else:
                logger.error("Evaluator returned no messages.")
                break

            if not evaluator_raw_output:
                logger.error(f"Evaluator returned an empty message in iteration {iteration}.")
                break

            logger.info(f"Evaluator Raw Output (Iteration {iteration}):\n{evaluator_raw_output}")
            
            # Extract JSON from markdown code block if present
            json_string_evaluator = evaluator_raw_output.strip()
            if json_string_evaluator.startswith("```json"):
                json_string_evaluator = json_string_evaluator[len("```json"):].strip()
                if json_string_evaluator.endswith("```"):
                    json_string_evaluator = json_string_evaluator[:-len("```")].strip()

            evaluator_feedback_data = json.loads(json_string_evaluator)
            evaluator_feedback_obj = EvaluationFeedback(**evaluator_feedback_data)
            logger.info(f"Evaluator score: {evaluator_feedback_obj.score}")

            if evaluator_feedback_obj.score == "pass":
                logger.info("Domain model is satisfactory, exiting workflow.")
                break
            else:
                logger.info(f"Feedback: {evaluator_feedback_obj.feedback}")
                logger.info("Re-running modeling with feedback...")

        except json.JSONDecodeError:
            logger.exception(f"Evaluator's output was not valid JSON in iteration {iteration}. Raw output: {evaluator_raw_output}")
            break
        except (KeyError, IndexError):
            logger.exception(f"Missing expected key or empty history in evaluator's output in iteration {iteration}. Raw output: {evaluator_raw_output}")
            break
        except Exception:
            logger.exception(f"An unexpected error occurred during evaluation in iteration {iteration}. Raw output: {evaluator_raw_output}")
            break
    else:
        logger.info(f"Max iterations ({max_iterations}) reached. Workflow stopped.")

    logger.info("Domain Modeling Workflow Complete.")


if __name__ == "__main__":
    # Ensure you have an event loop to run the async main function.
    # In a typical script, asyncio.run() handles this.
    try:
        asyncio.run(main())
    except Exception as e:
        logging.getLogger().critical(f"Failed to run the async main function: {e}", exc_info=True)