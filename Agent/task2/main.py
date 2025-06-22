import json
from openai import OpenAI
from openai.types.chat import ChatCompletion
from config import API_KEY, BASE_URL


def query_llm(client: OpenAI, prompt: str, model: str = "gpt-4o") -> str:
    try:
        completion: ChatCompletion = client.chat.completions.create(
            model=model,
            messages=[
                {"role": "user", "content": prompt}
            ]
        )
        return completion.choices[0].message.content.strip()
    except Exception as e:
        return f"请求失败: {str(e)}"


def format_output(content: str) -> None:
    print("=== LLM 回复内容 ===")
    print(content)
    print("===================")


if __name__ == "__main__":
    user_prompt: str = """
你是一个经验丰富的软件需求分析师。
请根据以下背景信息，为**学校会议室预订管理系统**建立详细的需求模型。

该系统旨在简化和自动化学校内部会议室的预订与管理流程，提升会议室资源的利用效率。

**背景信息：**
* **系统面向用户：** 学生、教师、管理员。
* **会议室资源：** 系统需管理多个会议室，每个会议室具有以下属性：
    * **容量：** 可容纳的人数。
    * **设备：** 例如投影仪、麦克风、白板等列表。
    * **位置：** 会议室所在的具体位置。
    * **预订时间表：** 记录了每个会议室在不同时间段的预订情况。
* **预订流程核心：** 用户能够查询会议室可用性，提交预订申请；管理员负责对预订申请进行审批与管理。
* **预订规则（核心业务逻辑）：**
    * **时间限制：** 预订必须在指定的服务时间内进行（例如：8:00 - 22:00）。
    * **时长限制：** 单次预订有最小和最大时长限制（例如：最少30分钟，最多4小时）。
    * **冲突检测：** 同一会议室在同一时间段不能被重复预订。
    * **权限限制：** 不同用户角色可能有不同的预订额度或可预订的会议室类型。
    * **提前预订限制：** 预订必须在会议开始前至少X小时提交，且不能超过Y天进行预订。
* **系统功能（用户可执行的操作）：**
    * **用户管理：** 包括用户注册和登录。
    * **会议室信息展示：** 用户可以查看会议室的详细信息（如容量、设备、位置）。
    * **会议室可用性查询：** 用户可以根据日期、时间、容量、设备等条件查询可用的会议室。
    * **预订申请提交：** 用户提交预订时需提供会议主题、预期参会人数、起止时间等信息。
    * **预订申请审批：** 管理员可以查看所有待审批的预订申请，并进行批准或拒绝操作。
    * **预订信息管理：** 用户可以查看自己提交的预订，并在规定时间内修改或取消。
    * **系统权限管理：** 维护不同用户角色的权限配置。
    * **会议室使用情况统计：** 管理员可以查看会议室的利用率、热门时段等统计数据。

请基于上述信息，输出一个详细的需求模型，使用 **JSON 格式**。JSON 输出应包含以下顶级键：

1.  `user_roles`：列出所有用户角色及其主要职责。
2.  `use_cases`：描述系统的主要用例，每个用例应包含 `name` (用例名称)、`actors` (参与者列表) 和 `description` (简要描述)。
3.  `data_entities`：列出系统涉及的主要数据实体及其关键属性。每个实体应包含 `name` 和 `attributes` (属性列表，每个属性包含 `name` 和 `type`)。
4.  `business_rules`：描述系统需要遵循的业务规则，以清晰的文本列表形式呈现。

请确保输出的JSON结构正确，并且内容完整、准确地反映了上述背景信息。
    """
    client: OpenAI = OpenAI(api_key=API_KEY, base_url=BASE_URL)
    llm_response: str = query_llm(client, user_prompt)
    format_output(llm_response)