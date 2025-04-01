package gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TabPane;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.Tooltip;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import java.io.File;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.List;
import java.time.LocalDate;
import java.util.LinkedList;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import gui.supportclass.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.util.Callback;
import services.*;
import services.impl.*;
import java.time.format.DateTimeFormatter;
import java.lang.reflect.Method;

import entities.*;

public class PrototypeController implements Initializable {


	DateTimeFormatter dateformatter;
	 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		gymsystemsystem_service = ServiceManager.createGymSystemSystem();
		thirdpartyservices_service = ServiceManager.createThirdPartyServices();
		cancel_book_courseservice_service = ServiceManager.createCancel_book_CourseService();
		book_courseservice_service = ServiceManager.createBook_CourseService();
		loginservice_service = ServiceManager.createLoginService();
		register_memberservice_service = ServiceManager.createRegister_memberService();
		register_trainerservice_service = ServiceManager.createRegister_trainerService();
		create_courseservice_service = ServiceManager.createCreate_CourseService();
		update_scheduleservice_service = ServiceManager.createUpdate_scheduleService();
		finish_courseservice_service = ServiceManager.createFinish_CourseService();
				
		this.dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
	   	 //prepare data for contract
	   	 prepareData();
	   	 
	   	 //generate invariant panel
	   	 genereateInvairantPanel();
	   	 
		 //Actor Threeview Binding
		 actorTreeViewBinding();
		 
		 //Generate
		 generatOperationPane();
		 genereateOpInvariantPanel();
		 
		 //prilimariry data
		 try {
			DataFitService.fit();
		 } catch (PreconditionException e) {
			// TODO Auto-generated catch block
		 	e.printStackTrace();
		 }
		 
		 //generate class statistic
		 classStatisicBingding();
		 
		 //generate object statistic
		 generateObjectTable();
		 
		 //genereate association statistic
		 associationStatisicBingding();

		 //set listener 
		 setListeners();
	}
	
	/**
	 * deepCopyforTreeItem (Actor Generation)
	 */
	TreeItem<String> deepCopyTree(TreeItem<String> item) {
		    TreeItem<String> copy = new TreeItem<String>(item.getValue());
		    for (TreeItem<String> child : item.getChildren()) {
		        copy.getChildren().add(deepCopyTree(child));
		    }
		    return copy;
	}
	
	/**
	 * check all invariant and update invariant panel
	 */
	public void invairantPanelUpdate() {
		
		try {
			
			for (Entry<String, Label> inv : entity_invariants_label_map.entrySet()) {
				String invname = inv.getKey();
				String[] invt = invname.split("_");
				String entityName = invt[0];
				for (Object o : EntityManager.getAllInstancesOf(entityName)) {				
					 Method m = o.getClass().getMethod(invname);
					 if ((boolean)m.invoke(o) == false) {
						 inv.getValue().setStyle("-fx-max-width: Infinity;" + 
									"-fx-background-color: linear-gradient(to right, #7FFF00 0%, #af0c27 100%);" +
								    "-fx-padding: 6px;" +
								    "-fx-border-color: black;");
						 break;
					 }
				}				
			}
			
			for (Entry<String, Label> inv : service_invariants_label_map.entrySet()) {
				String invname = inv.getKey();
				String[] invt = invname.split("_");
				String serviceName = invt[0];
				for (Object o : ServiceManager.getAllInstancesOf(serviceName)) {				
					 Method m = o.getClass().getMethod(invname);
					 if ((boolean)m.invoke(o) == false) {
						 inv.getValue().setStyle("-fx-max-width: Infinity;" + 
									"-fx-background-color: linear-gradient(to right, #7FFF00 0%, #af0c27 100%);" +
								    "-fx-padding: 6px;" +
								    "-fx-border-color: black;");
						 break;
					 }
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	

	/**
	 * check op invariant and update op invariant panel
	 */		
	public void opInvairantPanelUpdate() {
		
		try {
			
			for (Entry<String, Label> inv : op_entity_invariants_label_map.entrySet()) {
				String invname = inv.getKey();
				String[] invt = invname.split("_");
				String entityName = invt[0];
				for (Object o : EntityManager.getAllInstancesOf(entityName)) {
					 Method m = o.getClass().getMethod(invname);
					 if ((boolean)m.invoke(o) == false) {
						 inv.getValue().setStyle("-fx-max-width: Infinity;" + 
									"-fx-background-color: linear-gradient(to right, #7FFF00 0%, #af0c27 100%);" +
								    "-fx-padding: 6px;" +
								    "-fx-border-color: black;");
						 break;
					 }
				}
			}
			
			for (Entry<String, Label> inv : op_service_invariants_label_map.entrySet()) {
				String invname = inv.getKey();
				String[] invt = invname.split("_");
				String serviceName = invt[0];
				for (Object o : ServiceManager.getAllInstancesOf(serviceName)) {
					 Method m = o.getClass().getMethod(invname);
					 if ((boolean)m.invoke(o) == false) {
						 inv.getValue().setStyle("-fx-max-width: Infinity;" + 
									"-fx-background-color: linear-gradient(to right, #7FFF00 0%, #af0c27 100%);" +
								    "-fx-padding: 6px;" +
								    "-fx-border-color: black;");
						 break;
					 }
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* 
	*	generate op invariant panel 
	*/
	public void genereateOpInvariantPanel() {
		
		opInvariantPanel = new HashMap<String, VBox>();
		op_entity_invariants_label_map = new LinkedHashMap<String, Label>();
		op_service_invariants_label_map = new LinkedHashMap<String, Label>();
		
		VBox v;
		List<String> entities;
		v = new VBox();
		
		//entities invariants
		entities = Cancel_book_CourseServiceImpl.opINVRelatedEntity.get("listAllCourseBooked");
		if (entities != null) {
			for (String opRelatedEntities : entities) {
				for (Entry<String, String> inv : entity_invariants_map.entrySet()) {
					
					String invname = inv.getKey();
					String[] invt = invname.split("_");
					String entityName = invt[0];
		
					if (opRelatedEntities.equals(entityName)) {
						Label l = new Label(invname);
						l.setStyle("-fx-max-width: Infinity;" + 
								"-fx-background-color: linear-gradient(to right, #7FFF00 0%, #F0FFFF 100%);" +
							    "-fx-padding: 6px;" +
							    "-fx-border-color: black;");
						Tooltip tp = new Tooltip();
						tp.setText(inv.getValue());
						l.setTooltip(tp);
						
						op_entity_invariants_label_map.put(invname, l);
						
						v.getChildren().add(l);
					}
				}
			}
		} else {
			Label l = new Label("listAllCourseBooked" + " is no related invariants");
			l.setPadding(new Insets(8, 8, 8, 8));
			v.getChildren().add(l);
		}
		
		//service invariants
		for (Entry<String, String> inv : service_invariants_map.entrySet()) {
			
			String invname = inv.getKey();
			String[] invt = invname.split("_");
			String serviceName = invt[0];
			
			if (serviceName.equals("Cancel_book_CourseService")) {
				Label l = new Label(invname);
				l.setStyle("-fx-max-width: Infinity;" + 
						   "-fx-background-color: linear-gradient(to right, #7FFF00 0%, #F0FFFF 100%);" +
						   "-fx-padding: 6px;" +
						   "-fx-border-color: black;");
				Tooltip tp = new Tooltip();
				tp.setText(inv.getValue());
				l.setTooltip(tp);
				
				op_entity_invariants_label_map.put(invname, l);
				
				v.getChildren().add(l);
			}
		}
		opInvariantPanel.put("listAllCourseBooked", v);
		
		v = new VBox();
		
		//entities invariants
		entities = Cancel_book_CourseServiceImpl.opINVRelatedEntity.get("confirmCancelBook");
		if (entities != null) {
			for (String opRelatedEntities : entities) {
				for (Entry<String, String> inv : entity_invariants_map.entrySet()) {
					
					String invname = inv.getKey();
					String[] invt = invname.split("_");
					String entityName = invt[0];
		
					if (opRelatedEntities.equals(entityName)) {
						Label l = new Label(invname);
						l.setStyle("-fx-max-width: Infinity;" + 
								"-fx-background-color: linear-gradient(to right, #7FFF00 0%, #F0FFFF 100%);" +
							    "-fx-padding: 6px;" +
							    "-fx-border-color: black;");
						Tooltip tp = new Tooltip();
						tp.setText(inv.getValue());
						l.setTooltip(tp);
						
						op_entity_invariants_label_map.put(invname, l);
						
						v.getChildren().add(l);
					}
				}
			}
		} else {
			Label l = new Label("confirmCancelBook" + " is no related invariants");
			l.setPadding(new Insets(8, 8, 8, 8));
			v.getChildren().add(l);
		}
		
		//service invariants
		for (Entry<String, String> inv : service_invariants_map.entrySet()) {
			
			String invname = inv.getKey();
			String[] invt = invname.split("_");
			String serviceName = invt[0];
			
			if (serviceName.equals("Cancel_book_CourseService")) {
				Label l = new Label(invname);
				l.setStyle("-fx-max-width: Infinity;" + 
						   "-fx-background-color: linear-gradient(to right, #7FFF00 0%, #F0FFFF 100%);" +
						   "-fx-padding: 6px;" +
						   "-fx-border-color: black;");
				Tooltip tp = new Tooltip();
				tp.setText(inv.getValue());
				l.setTooltip(tp);
				
				op_entity_invariants_label_map.put(invname, l);
				
				v.getChildren().add(l);
			}
		}
		opInvariantPanel.put("confirmCancelBook", v);
		
		v = new VBox();
		
		//entities invariants
		entities = Cancel_book_CourseServiceImpl.opINVRelatedEntity.get("requestRefund");
		if (entities != null) {
			for (String opRelatedEntities : entities) {
				for (Entry<String, String> inv : entity_invariants_map.entrySet()) {
					
					String invname = inv.getKey();
					String[] invt = invname.split("_");
					String entityName = invt[0];
		
					if (opRelatedEntities.equals(entityName)) {
						Label l = new Label(invname);
						l.setStyle("-fx-max-width: Infinity;" + 
								"-fx-background-color: linear-gradient(to right, #7FFF00 0%, #F0FFFF 100%);" +
							    "-fx-padding: 6px;" +
							    "-fx-border-color: black;");
						Tooltip tp = new Tooltip();
						tp.setText(inv.getValue());
						l.setTooltip(tp);
						
						op_entity_invariants_label_map.put(invname, l);
						
						v.getChildren().add(l);
					}
				}
			}
		} else {
			Label l = new Label("requestRefund" + " is no related invariants");
			l.setPadding(new Insets(8, 8, 8, 8));
			v.getChildren().add(l);
		}
		
		//service invariants
		for (Entry<String, String> inv : service_invariants_map.entrySet()) {
			
			String invname = inv.getKey();
			String[] invt = invname.split("_");
			String serviceName = invt[0];
			
			if (serviceName.equals("Cancel_book_CourseService")) {
				Label l = new Label(invname);
				l.setStyle("-fx-max-width: Infinity;" + 
						   "-fx-background-color: linear-gradient(to right, #7FFF00 0%, #F0FFFF 100%);" +
						   "-fx-padding: 6px;" +
						   "-fx-border-color: black;");
				Tooltip tp = new Tooltip();
				tp.setText(inv.getValue());
				l.setTooltip(tp);
				
				op_entity_invariants_label_map.put(invname, l);
				
				v.getChildren().add(l);
			}
		}
		opInvariantPanel.put("requestRefund", v);
		
		v = new VBox();
		
		//entities invariants
		entities = Book_CourseServiceImpl.opINVRelatedEntity.get("listAllCourseAvailable");
		if (entities != null) {
			for (String opRelatedEntities : entities) {
				for (Entry<String, String> inv : entity_invariants_map.entrySet()) {
					
					String invname = inv.getKey();
					String[] invt = invname.split("_");
					String entityName = invt[0];
		
					if (opRelatedEntities.equals(entityName)) {
						Label l = new Label(invname);
						l.setStyle("-fx-max-width: Infinity;" + 
								"-fx-background-color: linear-gradient(to right, #7FFF00 0%, #F0FFFF 100%);" +
							    "-fx-padding: 6px;" +
							    "-fx-border-color: black;");
						Tooltip tp = new Tooltip();
						tp.setText(inv.getValue());
						l.setTooltip(tp);
						
						op_entity_invariants_label_map.put(invname, l);
						
						v.getChildren().add(l);
					}
				}
			}
		} else {
			Label l = new Label("listAllCourseAvailable" + " is no related invariants");
			l.setPadding(new Insets(8, 8, 8, 8));
			v.getChildren().add(l);
		}
		
		//service invariants
		for (Entry<String, String> inv : service_invariants_map.entrySet()) {
			
			String invname = inv.getKey();
			String[] invt = invname.split("_");
			String serviceName = invt[0];
			
			if (serviceName.equals("Book_CourseService")) {
				Label l = new Label(invname);
				l.setStyle("-fx-max-width: Infinity;" + 
						   "-fx-background-color: linear-gradient(to right, #7FFF00 0%, #F0FFFF 100%);" +
						   "-fx-padding: 6px;" +
						   "-fx-border-color: black;");
				Tooltip tp = new Tooltip();
				tp.setText(inv.getValue());
				l.setTooltip(tp);
				
				op_entity_invariants_label_map.put(invname, l);
				
				v.getChildren().add(l);
			}
		}
		opInvariantPanel.put("listAllCourseAvailable", v);
		
		v = new VBox();
		
		//entities invariants
		entities = Book_CourseServiceImpl.opINVRelatedEntity.get("chooseOneBooking");
		if (entities != null) {
			for (String opRelatedEntities : entities) {
				for (Entry<String, String> inv : entity_invariants_map.entrySet()) {
					
					String invname = inv.getKey();
					String[] invt = invname.split("_");
					String entityName = invt[0];
		
					if (opRelatedEntities.equals(entityName)) {
						Label l = new Label(invname);
						l.setStyle("-fx-max-width: Infinity;" + 
								"-fx-background-color: linear-gradient(to right, #7FFF00 0%, #F0FFFF 100%);" +
							    "-fx-padding: 6px;" +
							    "-fx-border-color: black;");
						Tooltip tp = new Tooltip();
						tp.setText(inv.getValue());
						l.setTooltip(tp);
						
						op_entity_invariants_label_map.put(invname, l);
						
						v.getChildren().add(l);
					}
				}
			}
		} else {
			Label l = new Label("chooseOneBooking" + " is no related invariants");
			l.setPadding(new Insets(8, 8, 8, 8));
			v.getChildren().add(l);
		}
		
		//service invariants
		for (Entry<String, String> inv : service_invariants_map.entrySet()) {
			
			String invname = inv.getKey();
			String[] invt = invname.split("_");
			String serviceName = invt[0];
			
			if (serviceName.equals("Book_CourseService")) {
				Label l = new Label(invname);
				l.setStyle("-fx-max-width: Infinity;" + 
						   "-fx-background-color: linear-gradient(to right, #7FFF00 0%, #F0FFFF 100%);" +
						   "-fx-padding: 6px;" +
						   "-fx-border-color: black;");
				Tooltip tp = new Tooltip();
				tp.setText(inv.getValue());
				l.setTooltip(tp);
				
				op_entity_invariants_label_map.put(invname, l);
				
				v.getChildren().add(l);
			}
		}
		opInvariantPanel.put("chooseOneBooking", v);
		
		v = new VBox();
		
		//entities invariants
		entities = Book_CourseServiceImpl.opINVRelatedEntity.get("payFee");
		if (entities != null) {
			for (String opRelatedEntities : entities) {
				for (Entry<String, String> inv : entity_invariants_map.entrySet()) {
					
					String invname = inv.getKey();
					String[] invt = invname.split("_");
					String entityName = invt[0];
		
					if (opRelatedEntities.equals(entityName)) {
						Label l = new Label(invname);
						l.setStyle("-fx-max-width: Infinity;" + 
								"-fx-background-color: linear-gradient(to right, #7FFF00 0%, #F0FFFF 100%);" +
							    "-fx-padding: 6px;" +
							    "-fx-border-color: black;");
						Tooltip tp = new Tooltip();
						tp.setText(inv.getValue());
						l.setTooltip(tp);
						
						op_entity_invariants_label_map.put(invname, l);
						
						v.getChildren().add(l);
					}
				}
			}
		} else {
			Label l = new Label("payFee" + " is no related invariants");
			l.setPadding(new Insets(8, 8, 8, 8));
			v.getChildren().add(l);
		}
		
		//service invariants
		for (Entry<String, String> inv : service_invariants_map.entrySet()) {
			
			String invname = inv.getKey();
			String[] invt = invname.split("_");
			String serviceName = invt[0];
			
			if (serviceName.equals("Book_CourseService")) {
				Label l = new Label(invname);
				l.setStyle("-fx-max-width: Infinity;" + 
						   "-fx-background-color: linear-gradient(to right, #7FFF00 0%, #F0FFFF 100%);" +
						   "-fx-padding: 6px;" +
						   "-fx-border-color: black;");
				Tooltip tp = new Tooltip();
				tp.setText(inv.getValue());
				l.setTooltip(tp);
				
				op_entity_invariants_label_map.put(invname, l);
				
				v.getChildren().add(l);
			}
		}
		opInvariantPanel.put("payFee", v);
		
		v = new VBox();
		
		//entities invariants
		entities = LoginServiceImpl.opINVRelatedEntity.get("login_member");
		if (entities != null) {
			for (String opRelatedEntities : entities) {
				for (Entry<String, String> inv : entity_invariants_map.entrySet()) {
					
					String invname = inv.getKey();
					String[] invt = invname.split("_");
					String entityName = invt[0];
		
					if (opRelatedEntities.equals(entityName)) {
						Label l = new Label(invname);
						l.setStyle("-fx-max-width: Infinity;" + 
								"-fx-background-color: linear-gradient(to right, #7FFF00 0%, #F0FFFF 100%);" +
							    "-fx-padding: 6px;" +
							    "-fx-border-color: black;");
						Tooltip tp = new Tooltip();
						tp.setText(inv.getValue());
						l.setTooltip(tp);
						
						op_entity_invariants_label_map.put(invname, l);
						
						v.getChildren().add(l);
					}
				}
			}
		} else {
			Label l = new Label("login_member" + " is no related invariants");
			l.setPadding(new Insets(8, 8, 8, 8));
			v.getChildren().add(l);
		}
		
		//service invariants
		for (Entry<String, String> inv : service_invariants_map.entrySet()) {
			
			String invname = inv.getKey();
			String[] invt = invname.split("_");
			String serviceName = invt[0];
			
			if (serviceName.equals("LoginService")) {
				Label l = new Label(invname);
				l.setStyle("-fx-max-width: Infinity;" + 
						   "-fx-background-color: linear-gradient(to right, #7FFF00 0%, #F0FFFF 100%);" +
						   "-fx-padding: 6px;" +
						   "-fx-border-color: black;");
				Tooltip tp = new Tooltip();
				tp.setText(inv.getValue());
				l.setTooltip(tp);
				
				op_entity_invariants_label_map.put(invname, l);
				
				v.getChildren().add(l);
			}
		}
		opInvariantPanel.put("login_member", v);
		
		v = new VBox();
		
		//entities invariants
		entities = LoginServiceImpl.opINVRelatedEntity.get("login_trainer");
		if (entities != null) {
			for (String opRelatedEntities : entities) {
				for (Entry<String, String> inv : entity_invariants_map.entrySet()) {
					
					String invname = inv.getKey();
					String[] invt = invname.split("_");
					String entityName = invt[0];
		
					if (opRelatedEntities.equals(entityName)) {
						Label l = new Label(invname);
						l.setStyle("-fx-max-width: Infinity;" + 
								"-fx-background-color: linear-gradient(to right, #7FFF00 0%, #F0FFFF 100%);" +
							    "-fx-padding: 6px;" +
							    "-fx-border-color: black;");
						Tooltip tp = new Tooltip();
						tp.setText(inv.getValue());
						l.setTooltip(tp);
						
						op_entity_invariants_label_map.put(invname, l);
						
						v.getChildren().add(l);
					}
				}
			}
		} else {
			Label l = new Label("login_trainer" + " is no related invariants");
			l.setPadding(new Insets(8, 8, 8, 8));
			v.getChildren().add(l);
		}
		
		//service invariants
		for (Entry<String, String> inv : service_invariants_map.entrySet()) {
			
			String invname = inv.getKey();
			String[] invt = invname.split("_");
			String serviceName = invt[0];
			
			if (serviceName.equals("LoginService")) {
				Label l = new Label(invname);
				l.setStyle("-fx-max-width: Infinity;" + 
						   "-fx-background-color: linear-gradient(to right, #7FFF00 0%, #F0FFFF 100%);" +
						   "-fx-padding: 6px;" +
						   "-fx-border-color: black;");
				Tooltip tp = new Tooltip();
				tp.setText(inv.getValue());
				l.setTooltip(tp);
				
				op_entity_invariants_label_map.put(invname, l);
				
				v.getChildren().add(l);
			}
		}
		opInvariantPanel.put("login_trainer", v);
		
		v = new VBox();
		
		//entities invariants
		entities = LoginServiceImpl.opINVRelatedEntity.get("login_frontdesk");
		if (entities != null) {
			for (String opRelatedEntities : entities) {
				for (Entry<String, String> inv : entity_invariants_map.entrySet()) {
					
					String invname = inv.getKey();
					String[] invt = invname.split("_");
					String entityName = invt[0];
		
					if (opRelatedEntities.equals(entityName)) {
						Label l = new Label(invname);
						l.setStyle("-fx-max-width: Infinity;" + 
								"-fx-background-color: linear-gradient(to right, #7FFF00 0%, #F0FFFF 100%);" +
							    "-fx-padding: 6px;" +
							    "-fx-border-color: black;");
						Tooltip tp = new Tooltip();
						tp.setText(inv.getValue());
						l.setTooltip(tp);
						
						op_entity_invariants_label_map.put(invname, l);
						
						v.getChildren().add(l);
					}
				}
			}
		} else {
			Label l = new Label("login_frontdesk" + " is no related invariants");
			l.setPadding(new Insets(8, 8, 8, 8));
			v.getChildren().add(l);
		}
		
		//service invariants
		for (Entry<String, String> inv : service_invariants_map.entrySet()) {
			
			String invname = inv.getKey();
			String[] invt = invname.split("_");
			String serviceName = invt[0];
			
			if (serviceName.equals("LoginService")) {
				Label l = new Label(invname);
				l.setStyle("-fx-max-width: Infinity;" + 
						   "-fx-background-color: linear-gradient(to right, #7FFF00 0%, #F0FFFF 100%);" +
						   "-fx-padding: 6px;" +
						   "-fx-border-color: black;");
				Tooltip tp = new Tooltip();
				tp.setText(inv.getValue());
				l.setTooltip(tp);
				
				op_entity_invariants_label_map.put(invname, l);
				
				v.getChildren().add(l);
			}
		}
		opInvariantPanel.put("login_frontdesk", v);
		
		v = new VBox();
		
		//entities invariants
		entities = Register_memberServiceImpl.opINVRelatedEntity.get("input_member_info");
		if (entities != null) {
			for (String opRelatedEntities : entities) {
				for (Entry<String, String> inv : entity_invariants_map.entrySet()) {
					
					String invname = inv.getKey();
					String[] invt = invname.split("_");
					String entityName = invt[0];
		
					if (opRelatedEntities.equals(entityName)) {
						Label l = new Label(invname);
						l.setStyle("-fx-max-width: Infinity;" + 
								"-fx-background-color: linear-gradient(to right, #7FFF00 0%, #F0FFFF 100%);" +
							    "-fx-padding: 6px;" +
							    "-fx-border-color: black;");
						Tooltip tp = new Tooltip();
						tp.setText(inv.getValue());
						l.setTooltip(tp);
						
						op_entity_invariants_label_map.put(invname, l);
						
						v.getChildren().add(l);
					}
				}
			}
		} else {
			Label l = new Label("input_member_info" + " is no related invariants");
			l.setPadding(new Insets(8, 8, 8, 8));
			v.getChildren().add(l);
		}
		
		//service invariants
		for (Entry<String, String> inv : service_invariants_map.entrySet()) {
			
			String invname = inv.getKey();
			String[] invt = invname.split("_");
			String serviceName = invt[0];
			
			if (serviceName.equals("Register_memberService")) {
				Label l = new Label(invname);
				l.setStyle("-fx-max-width: Infinity;" + 
						   "-fx-background-color: linear-gradient(to right, #7FFF00 0%, #F0FFFF 100%);" +
						   "-fx-padding: 6px;" +
						   "-fx-border-color: black;");
				Tooltip tp = new Tooltip();
				tp.setText(inv.getValue());
				l.setTooltip(tp);
				
				op_entity_invariants_label_map.put(invname, l);
				
				v.getChildren().add(l);
			}
		}
		opInvariantPanel.put("input_member_info", v);
		
		v = new VBox();
		
		//entities invariants
		entities = Register_trainerServiceImpl.opINVRelatedEntity.get("input_trainer_info");
		if (entities != null) {
			for (String opRelatedEntities : entities) {
				for (Entry<String, String> inv : entity_invariants_map.entrySet()) {
					
					String invname = inv.getKey();
					String[] invt = invname.split("_");
					String entityName = invt[0];
		
					if (opRelatedEntities.equals(entityName)) {
						Label l = new Label(invname);
						l.setStyle("-fx-max-width: Infinity;" + 
								"-fx-background-color: linear-gradient(to right, #7FFF00 0%, #F0FFFF 100%);" +
							    "-fx-padding: 6px;" +
							    "-fx-border-color: black;");
						Tooltip tp = new Tooltip();
						tp.setText(inv.getValue());
						l.setTooltip(tp);
						
						op_entity_invariants_label_map.put(invname, l);
						
						v.getChildren().add(l);
					}
				}
			}
		} else {
			Label l = new Label("input_trainer_info" + " is no related invariants");
			l.setPadding(new Insets(8, 8, 8, 8));
			v.getChildren().add(l);
		}
		
		//service invariants
		for (Entry<String, String> inv : service_invariants_map.entrySet()) {
			
			String invname = inv.getKey();
			String[] invt = invname.split("_");
			String serviceName = invt[0];
			
			if (serviceName.equals("Register_trainerService")) {
				Label l = new Label(invname);
				l.setStyle("-fx-max-width: Infinity;" + 
						   "-fx-background-color: linear-gradient(to right, #7FFF00 0%, #F0FFFF 100%);" +
						   "-fx-padding: 6px;" +
						   "-fx-border-color: black;");
				Tooltip tp = new Tooltip();
				tp.setText(inv.getValue());
				l.setTooltip(tp);
				
				op_entity_invariants_label_map.put(invname, l);
				
				v.getChildren().add(l);
			}
		}
		opInvariantPanel.put("input_trainer_info", v);
		
		v = new VBox();
		
		//entities invariants
		entities = Create_CourseServiceImpl.opINVRelatedEntity.get("requestCreateCourse");
		if (entities != null) {
			for (String opRelatedEntities : entities) {
				for (Entry<String, String> inv : entity_invariants_map.entrySet()) {
					
					String invname = inv.getKey();
					String[] invt = invname.split("_");
					String entityName = invt[0];
		
					if (opRelatedEntities.equals(entityName)) {
						Label l = new Label(invname);
						l.setStyle("-fx-max-width: Infinity;" + 
								"-fx-background-color: linear-gradient(to right, #7FFF00 0%, #F0FFFF 100%);" +
							    "-fx-padding: 6px;" +
							    "-fx-border-color: black;");
						Tooltip tp = new Tooltip();
						tp.setText(inv.getValue());
						l.setTooltip(tp);
						
						op_entity_invariants_label_map.put(invname, l);
						
						v.getChildren().add(l);
					}
				}
			}
		} else {
			Label l = new Label("requestCreateCourse" + " is no related invariants");
			l.setPadding(new Insets(8, 8, 8, 8));
			v.getChildren().add(l);
		}
		
		//service invariants
		for (Entry<String, String> inv : service_invariants_map.entrySet()) {
			
			String invname = inv.getKey();
			String[] invt = invname.split("_");
			String serviceName = invt[0];
			
			if (serviceName.equals("Create_CourseService")) {
				Label l = new Label(invname);
				l.setStyle("-fx-max-width: Infinity;" + 
						   "-fx-background-color: linear-gradient(to right, #7FFF00 0%, #F0FFFF 100%);" +
						   "-fx-padding: 6px;" +
						   "-fx-border-color: black;");
				Tooltip tp = new Tooltip();
				tp.setText(inv.getValue());
				l.setTooltip(tp);
				
				op_entity_invariants_label_map.put(invname, l);
				
				v.getChildren().add(l);
			}
		}
		opInvariantPanel.put("requestCreateCourse", v);
		
		v = new VBox();
		
		//entities invariants
		entities = Create_CourseServiceImpl.opINVRelatedEntity.get("createRoom");
		if (entities != null) {
			for (String opRelatedEntities : entities) {
				for (Entry<String, String> inv : entity_invariants_map.entrySet()) {
					
					String invname = inv.getKey();
					String[] invt = invname.split("_");
					String entityName = invt[0];
		
					if (opRelatedEntities.equals(entityName)) {
						Label l = new Label(invname);
						l.setStyle("-fx-max-width: Infinity;" + 
								"-fx-background-color: linear-gradient(to right, #7FFF00 0%, #F0FFFF 100%);" +
							    "-fx-padding: 6px;" +
							    "-fx-border-color: black;");
						Tooltip tp = new Tooltip();
						tp.setText(inv.getValue());
						l.setTooltip(tp);
						
						op_entity_invariants_label_map.put(invname, l);
						
						v.getChildren().add(l);
					}
				}
			}
		} else {
			Label l = new Label("createRoom" + " is no related invariants");
			l.setPadding(new Insets(8, 8, 8, 8));
			v.getChildren().add(l);
		}
		
		//service invariants
		for (Entry<String, String> inv : service_invariants_map.entrySet()) {
			
			String invname = inv.getKey();
			String[] invt = invname.split("_");
			String serviceName = invt[0];
			
			if (serviceName.equals("Create_CourseService")) {
				Label l = new Label(invname);
				l.setStyle("-fx-max-width: Infinity;" + 
						   "-fx-background-color: linear-gradient(to right, #7FFF00 0%, #F0FFFF 100%);" +
						   "-fx-padding: 6px;" +
						   "-fx-border-color: black;");
				Tooltip tp = new Tooltip();
				tp.setText(inv.getValue());
				l.setTooltip(tp);
				
				op_entity_invariants_label_map.put(invname, l);
				
				v.getChildren().add(l);
			}
		}
		opInvariantPanel.put("createRoom", v);
		
		v = new VBox();
		
		//entities invariants
		entities = Create_CourseServiceImpl.opINVRelatedEntity.get("confirmCourseInfo");
		if (entities != null) {
			for (String opRelatedEntities : entities) {
				for (Entry<String, String> inv : entity_invariants_map.entrySet()) {
					
					String invname = inv.getKey();
					String[] invt = invname.split("_");
					String entityName = invt[0];
		
					if (opRelatedEntities.equals(entityName)) {
						Label l = new Label(invname);
						l.setStyle("-fx-max-width: Infinity;" + 
								"-fx-background-color: linear-gradient(to right, #7FFF00 0%, #F0FFFF 100%);" +
							    "-fx-padding: 6px;" +
							    "-fx-border-color: black;");
						Tooltip tp = new Tooltip();
						tp.setText(inv.getValue());
						l.setTooltip(tp);
						
						op_entity_invariants_label_map.put(invname, l);
						
						v.getChildren().add(l);
					}
				}
			}
		} else {
			Label l = new Label("confirmCourseInfo" + " is no related invariants");
			l.setPadding(new Insets(8, 8, 8, 8));
			v.getChildren().add(l);
		}
		
		//service invariants
		for (Entry<String, String> inv : service_invariants_map.entrySet()) {
			
			String invname = inv.getKey();
			String[] invt = invname.split("_");
			String serviceName = invt[0];
			
			if (serviceName.equals("Create_CourseService")) {
				Label l = new Label(invname);
				l.setStyle("-fx-max-width: Infinity;" + 
						   "-fx-background-color: linear-gradient(to right, #7FFF00 0%, #F0FFFF 100%);" +
						   "-fx-padding: 6px;" +
						   "-fx-border-color: black;");
				Tooltip tp = new Tooltip();
				tp.setText(inv.getValue());
				l.setTooltip(tp);
				
				op_entity_invariants_label_map.put(invname, l);
				
				v.getChildren().add(l);
			}
		}
		opInvariantPanel.put("confirmCourseInfo", v);
		
		v = new VBox();
		
		//entities invariants
		entities = Update_scheduleServiceImpl.opINVRelatedEntity.get("listCourseByTrainer");
		if (entities != null) {
			for (String opRelatedEntities : entities) {
				for (Entry<String, String> inv : entity_invariants_map.entrySet()) {
					
					String invname = inv.getKey();
					String[] invt = invname.split("_");
					String entityName = invt[0];
		
					if (opRelatedEntities.equals(entityName)) {
						Label l = new Label(invname);
						l.setStyle("-fx-max-width: Infinity;" + 
								"-fx-background-color: linear-gradient(to right, #7FFF00 0%, #F0FFFF 100%);" +
							    "-fx-padding: 6px;" +
							    "-fx-border-color: black;");
						Tooltip tp = new Tooltip();
						tp.setText(inv.getValue());
						l.setTooltip(tp);
						
						op_entity_invariants_label_map.put(invname, l);
						
						v.getChildren().add(l);
					}
				}
			}
		} else {
			Label l = new Label("listCourseByTrainer" + " is no related invariants");
			l.setPadding(new Insets(8, 8, 8, 8));
			v.getChildren().add(l);
		}
		
		//service invariants
		for (Entry<String, String> inv : service_invariants_map.entrySet()) {
			
			String invname = inv.getKey();
			String[] invt = invname.split("_");
			String serviceName = invt[0];
			
			if (serviceName.equals("Update_scheduleService")) {
				Label l = new Label(invname);
				l.setStyle("-fx-max-width: Infinity;" + 
						   "-fx-background-color: linear-gradient(to right, #7FFF00 0%, #F0FFFF 100%);" +
						   "-fx-padding: 6px;" +
						   "-fx-border-color: black;");
				Tooltip tp = new Tooltip();
				tp.setText(inv.getValue());
				l.setTooltip(tp);
				
				op_entity_invariants_label_map.put(invname, l);
				
				v.getChildren().add(l);
			}
		}
		opInvariantPanel.put("listCourseByTrainer", v);
		
		v = new VBox();
		
		//entities invariants
		entities = Update_scheduleServiceImpl.opINVRelatedEntity.get("chooseSpecificCourse");
		if (entities != null) {
			for (String opRelatedEntities : entities) {
				for (Entry<String, String> inv : entity_invariants_map.entrySet()) {
					
					String invname = inv.getKey();
					String[] invt = invname.split("_");
					String entityName = invt[0];
		
					if (opRelatedEntities.equals(entityName)) {
						Label l = new Label(invname);
						l.setStyle("-fx-max-width: Infinity;" + 
								"-fx-background-color: linear-gradient(to right, #7FFF00 0%, #F0FFFF 100%);" +
							    "-fx-padding: 6px;" +
							    "-fx-border-color: black;");
						Tooltip tp = new Tooltip();
						tp.setText(inv.getValue());
						l.setTooltip(tp);
						
						op_entity_invariants_label_map.put(invname, l);
						
						v.getChildren().add(l);
					}
				}
			}
		} else {
			Label l = new Label("chooseSpecificCourse" + " is no related invariants");
			l.setPadding(new Insets(8, 8, 8, 8));
			v.getChildren().add(l);
		}
		
		//service invariants
		for (Entry<String, String> inv : service_invariants_map.entrySet()) {
			
			String invname = inv.getKey();
			String[] invt = invname.split("_");
			String serviceName = invt[0];
			
			if (serviceName.equals("Update_scheduleService")) {
				Label l = new Label(invname);
				l.setStyle("-fx-max-width: Infinity;" + 
						   "-fx-background-color: linear-gradient(to right, #7FFF00 0%, #F0FFFF 100%);" +
						   "-fx-padding: 6px;" +
						   "-fx-border-color: black;");
				Tooltip tp = new Tooltip();
				tp.setText(inv.getValue());
				l.setTooltip(tp);
				
				op_entity_invariants_label_map.put(invname, l);
				
				v.getChildren().add(l);
			}
		}
		opInvariantPanel.put("chooseSpecificCourse", v);
		
		v = new VBox();
		
		//entities invariants
		entities = Update_scheduleServiceImpl.opINVRelatedEntity.get("confirmUpdateInfo");
		if (entities != null) {
			for (String opRelatedEntities : entities) {
				for (Entry<String, String> inv : entity_invariants_map.entrySet()) {
					
					String invname = inv.getKey();
					String[] invt = invname.split("_");
					String entityName = invt[0];
		
					if (opRelatedEntities.equals(entityName)) {
						Label l = new Label(invname);
						l.setStyle("-fx-max-width: Infinity;" + 
								"-fx-background-color: linear-gradient(to right, #7FFF00 0%, #F0FFFF 100%);" +
							    "-fx-padding: 6px;" +
							    "-fx-border-color: black;");
						Tooltip tp = new Tooltip();
						tp.setText(inv.getValue());
						l.setTooltip(tp);
						
						op_entity_invariants_label_map.put(invname, l);
						
						v.getChildren().add(l);
					}
				}
			}
		} else {
			Label l = new Label("confirmUpdateInfo" + " is no related invariants");
			l.setPadding(new Insets(8, 8, 8, 8));
			v.getChildren().add(l);
		}
		
		//service invariants
		for (Entry<String, String> inv : service_invariants_map.entrySet()) {
			
			String invname = inv.getKey();
			String[] invt = invname.split("_");
			String serviceName = invt[0];
			
			if (serviceName.equals("Update_scheduleService")) {
				Label l = new Label(invname);
				l.setStyle("-fx-max-width: Infinity;" + 
						   "-fx-background-color: linear-gradient(to right, #7FFF00 0%, #F0FFFF 100%);" +
						   "-fx-padding: 6px;" +
						   "-fx-border-color: black;");
				Tooltip tp = new Tooltip();
				tp.setText(inv.getValue());
				l.setTooltip(tp);
				
				op_entity_invariants_label_map.put(invname, l);
				
				v.getChildren().add(l);
			}
		}
		opInvariantPanel.put("confirmUpdateInfo", v);
		
		v = new VBox();
		
		//entities invariants
		entities = Finish_CourseServiceImpl.opINVRelatedEntity.get("listAllCourseByTrainer");
		if (entities != null) {
			for (String opRelatedEntities : entities) {
				for (Entry<String, String> inv : entity_invariants_map.entrySet()) {
					
					String invname = inv.getKey();
					String[] invt = invname.split("_");
					String entityName = invt[0];
		
					if (opRelatedEntities.equals(entityName)) {
						Label l = new Label(invname);
						l.setStyle("-fx-max-width: Infinity;" + 
								"-fx-background-color: linear-gradient(to right, #7FFF00 0%, #F0FFFF 100%);" +
							    "-fx-padding: 6px;" +
							    "-fx-border-color: black;");
						Tooltip tp = new Tooltip();
						tp.setText(inv.getValue());
						l.setTooltip(tp);
						
						op_entity_invariants_label_map.put(invname, l);
						
						v.getChildren().add(l);
					}
				}
			}
		} else {
			Label l = new Label("listAllCourseByTrainer" + " is no related invariants");
			l.setPadding(new Insets(8, 8, 8, 8));
			v.getChildren().add(l);
		}
		
		//service invariants
		for (Entry<String, String> inv : service_invariants_map.entrySet()) {
			
			String invname = inv.getKey();
			String[] invt = invname.split("_");
			String serviceName = invt[0];
			
			if (serviceName.equals("Finish_CourseService")) {
				Label l = new Label(invname);
				l.setStyle("-fx-max-width: Infinity;" + 
						   "-fx-background-color: linear-gradient(to right, #7FFF00 0%, #F0FFFF 100%);" +
						   "-fx-padding: 6px;" +
						   "-fx-border-color: black;");
				Tooltip tp = new Tooltip();
				tp.setText(inv.getValue());
				l.setTooltip(tp);
				
				op_entity_invariants_label_map.put(invname, l);
				
				v.getChildren().add(l);
			}
		}
		opInvariantPanel.put("listAllCourseByTrainer", v);
		
		v = new VBox();
		
		//entities invariants
		entities = Finish_CourseServiceImpl.opINVRelatedEntity.get("confirmCourseFinish");
		if (entities != null) {
			for (String opRelatedEntities : entities) {
				for (Entry<String, String> inv : entity_invariants_map.entrySet()) {
					
					String invname = inv.getKey();
					String[] invt = invname.split("_");
					String entityName = invt[0];
		
					if (opRelatedEntities.equals(entityName)) {
						Label l = new Label(invname);
						l.setStyle("-fx-max-width: Infinity;" + 
								"-fx-background-color: linear-gradient(to right, #7FFF00 0%, #F0FFFF 100%);" +
							    "-fx-padding: 6px;" +
							    "-fx-border-color: black;");
						Tooltip tp = new Tooltip();
						tp.setText(inv.getValue());
						l.setTooltip(tp);
						
						op_entity_invariants_label_map.put(invname, l);
						
						v.getChildren().add(l);
					}
				}
			}
		} else {
			Label l = new Label("confirmCourseFinish" + " is no related invariants");
			l.setPadding(new Insets(8, 8, 8, 8));
			v.getChildren().add(l);
		}
		
		//service invariants
		for (Entry<String, String> inv : service_invariants_map.entrySet()) {
			
			String invname = inv.getKey();
			String[] invt = invname.split("_");
			String serviceName = invt[0];
			
			if (serviceName.equals("Finish_CourseService")) {
				Label l = new Label(invname);
				l.setStyle("-fx-max-width: Infinity;" + 
						   "-fx-background-color: linear-gradient(to right, #7FFF00 0%, #F0FFFF 100%);" +
						   "-fx-padding: 6px;" +
						   "-fx-border-color: black;");
				Tooltip tp = new Tooltip();
				tp.setText(inv.getValue());
				l.setTooltip(tp);
				
				op_entity_invariants_label_map.put(invname, l);
				
				v.getChildren().add(l);
			}
		}
		opInvariantPanel.put("confirmCourseFinish", v);
		
		v = new VBox();
		
		//entities invariants
		entities = Finish_CourseServiceImpl.opINVRelatedEntity.get("requestBonus");
		if (entities != null) {
			for (String opRelatedEntities : entities) {
				for (Entry<String, String> inv : entity_invariants_map.entrySet()) {
					
					String invname = inv.getKey();
					String[] invt = invname.split("_");
					String entityName = invt[0];
		
					if (opRelatedEntities.equals(entityName)) {
						Label l = new Label(invname);
						l.setStyle("-fx-max-width: Infinity;" + 
								"-fx-background-color: linear-gradient(to right, #7FFF00 0%, #F0FFFF 100%);" +
							    "-fx-padding: 6px;" +
							    "-fx-border-color: black;");
						Tooltip tp = new Tooltip();
						tp.setText(inv.getValue());
						l.setTooltip(tp);
						
						op_entity_invariants_label_map.put(invname, l);
						
						v.getChildren().add(l);
					}
				}
			}
		} else {
			Label l = new Label("requestBonus" + " is no related invariants");
			l.setPadding(new Insets(8, 8, 8, 8));
			v.getChildren().add(l);
		}
		
		//service invariants
		for (Entry<String, String> inv : service_invariants_map.entrySet()) {
			
			String invname = inv.getKey();
			String[] invt = invname.split("_");
			String serviceName = invt[0];
			
			if (serviceName.equals("Finish_CourseService")) {
				Label l = new Label(invname);
				l.setStyle("-fx-max-width: Infinity;" + 
						   "-fx-background-color: linear-gradient(to right, #7FFF00 0%, #F0FFFF 100%);" +
						   "-fx-padding: 6px;" +
						   "-fx-border-color: black;");
				Tooltip tp = new Tooltip();
				tp.setText(inv.getValue());
				l.setTooltip(tp);
				
				op_entity_invariants_label_map.put(invname, l);
				
				v.getChildren().add(l);
			}
		}
		opInvariantPanel.put("requestBonus", v);
		
		
	}
	
	
	/*
	*  generate invariant panel
	*/
	public void genereateInvairantPanel() {
		
		service_invariants_label_map = new LinkedHashMap<String, Label>();
		entity_invariants_label_map = new LinkedHashMap<String, Label>();
		
		//entity_invariants_map
		VBox v = new VBox();
		//service invariants
		for (Entry<String, String> inv : service_invariants_map.entrySet()) {
			
			Label l = new Label(inv.getKey());
			l.setStyle("-fx-max-width: Infinity;" + 
					"-fx-background-color: linear-gradient(to right, #7FFF00 0%, #F0FFFF 100%);" +
				    "-fx-padding: 6px;" +
				    "-fx-border-color: black;");
			
			Tooltip tp = new Tooltip();
			tp.setText(inv.getValue());
			l.setTooltip(tp);
			
			service_invariants_label_map.put(inv.getKey(), l);
			v.getChildren().add(l);
			
		}
		//entity invariants
		for (Entry<String, String> inv : entity_invariants_map.entrySet()) {
			
			String INVname = inv.getKey();
			Label l = new Label(INVname);
			if (INVname.contains("AssociationInvariants")) {
				l.setStyle("-fx-max-width: Infinity;" + 
					"-fx-background-color: linear-gradient(to right, #099b17 0%, #F0FFFF 100%);" +
				    "-fx-padding: 6px;" +
				    "-fx-border-color: black;");
			} else {
				l.setStyle("-fx-max-width: Infinity;" + 
									"-fx-background-color: linear-gradient(to right, #7FFF00 0%, #F0FFFF 100%);" +
								    "-fx-padding: 6px;" +
								    "-fx-border-color: black;");
			}	
			Tooltip tp = new Tooltip();
			tp.setText(inv.getValue());
			l.setTooltip(tp);
			
			entity_invariants_label_map.put(inv.getKey(), l);
			v.getChildren().add(l);
			
		}
		ScrollPane scrollPane = new ScrollPane(v);
		scrollPane.setFitToWidth(true);
		all_invariant_pane.setMaxHeight(850);
		
		all_invariant_pane.setContent(scrollPane);
	}	
	
	
	
	/* 
	*	mainPane add listener
	*/
	public void setListeners() {
		 mainPane.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> {
			 
			 	if (newTab.getText().equals("System State")) {
			 		System.out.println("refresh all");
			 		refreshAll();
			 	}
		    
		    });
	}
	
	
	//checking all invariants
	public void checkAllInvariants() {
		
		invairantPanelUpdate();
	
	}	
	
	//refresh all
	public void refreshAll() {
		
		invairantPanelUpdate();
		classStatisticUpdate();
		generateObjectTable();
	}
	
	
	//update association
	public void updateAssociation(String className) {
		
		for (AssociationInfo assoc : allassociationData.get(className)) {
			assoc.computeAssociationNumber();
		}
		
	}
	
	public void updateAssociation(String className, int index) {
		
		for (AssociationInfo assoc : allassociationData.get(className)) {
			assoc.computeAssociationNumber(index);
		}
		
	}	
	
	public void generateObjectTable() {
		
		allObjectTables = new LinkedHashMap<String, TableView>();
		
		TableView<Map<String, String>> tableCourse = new TableView<Map<String, String>>();

		//super entity attribute column
						
		//attributes table column
		TableColumn<Map<String, String>, String> tableCourse_Id = new TableColumn<Map<String, String>, String>("Id");
		tableCourse_Id.setMinWidth("Id".length()*10);
		tableCourse_Id.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
			@Override
		    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
		        return new ReadOnlyStringWrapper(data.getValue().get("Id"));
		    }
		});	
		tableCourse.getColumns().add(tableCourse_Id);
		TableColumn<Map<String, String>, String> tableCourse_Name = new TableColumn<Map<String, String>, String>("Name");
		tableCourse_Name.setMinWidth("Name".length()*10);
		tableCourse_Name.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
			@Override
		    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
		        return new ReadOnlyStringWrapper(data.getValue().get("Name"));
		    }
		});	
		tableCourse.getColumns().add(tableCourse_Name);
		TableColumn<Map<String, String>, String> tableCourse_TrainerId = new TableColumn<Map<String, String>, String>("TrainerId");
		tableCourse_TrainerId.setMinWidth("TrainerId".length()*10);
		tableCourse_TrainerId.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
			@Override
		    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
		        return new ReadOnlyStringWrapper(data.getValue().get("TrainerId"));
		    }
		});	
		tableCourse.getColumns().add(tableCourse_TrainerId);
		TableColumn<Map<String, String>, String> tableCourse_Description = new TableColumn<Map<String, String>, String>("Description");
		tableCourse_Description.setMinWidth("Description".length()*10);
		tableCourse_Description.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
			@Override
		    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
		        return new ReadOnlyStringWrapper(data.getValue().get("Description"));
		    }
		});	
		tableCourse.getColumns().add(tableCourse_Description);
		TableColumn<Map<String, String>, String> tableCourse_RegisterStartTime = new TableColumn<Map<String, String>, String>("RegisterStartTime");
		tableCourse_RegisterStartTime.setMinWidth("RegisterStartTime".length()*10);
		tableCourse_RegisterStartTime.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
			@Override
		    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
		        return new ReadOnlyStringWrapper(data.getValue().get("RegisterStartTime"));
		    }
		});	
		tableCourse.getColumns().add(tableCourse_RegisterStartTime);
		TableColumn<Map<String, String>, String> tableCourse_RegisterEndTime = new TableColumn<Map<String, String>, String>("RegisterEndTime");
		tableCourse_RegisterEndTime.setMinWidth("RegisterEndTime".length()*10);
		tableCourse_RegisterEndTime.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
			@Override
		    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
		        return new ReadOnlyStringWrapper(data.getValue().get("RegisterEndTime"));
		    }
		});	
		tableCourse.getColumns().add(tableCourse_RegisterEndTime);
		TableColumn<Map<String, String>, String> tableCourse_EventStartTime = new TableColumn<Map<String, String>, String>("EventStartTime");
		tableCourse_EventStartTime.setMinWidth("EventStartTime".length()*10);
		tableCourse_EventStartTime.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
			@Override
		    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
		        return new ReadOnlyStringWrapper(data.getValue().get("EventStartTime"));
		    }
		});	
		tableCourse.getColumns().add(tableCourse_EventStartTime);
		TableColumn<Map<String, String>, String> tableCourse_EventEndTime = new TableColumn<Map<String, String>, String>("EventEndTime");
		tableCourse_EventEndTime.setMinWidth("EventEndTime".length()*10);
		tableCourse_EventEndTime.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
			@Override
		    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
		        return new ReadOnlyStringWrapper(data.getValue().get("EventEndTime"));
		    }
		});	
		tableCourse.getColumns().add(tableCourse_EventEndTime);
		TableColumn<Map<String, String>, String> tableCourse_Cost = new TableColumn<Map<String, String>, String>("Cost");
		tableCourse_Cost.setMinWidth("Cost".length()*10);
		tableCourse_Cost.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
			@Override
		    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
		        return new ReadOnlyStringWrapper(data.getValue().get("Cost"));
		    }
		});	
		tableCourse.getColumns().add(tableCourse_Cost);
		TableColumn<Map<String, String>, String> tableCourse_RoomId = new TableColumn<Map<String, String>, String>("RoomId");
		tableCourse_RoomId.setMinWidth("RoomId".length()*10);
		tableCourse_RoomId.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
			@Override
		    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
		        return new ReadOnlyStringWrapper(data.getValue().get("RoomId"));
		    }
		});	
		tableCourse.getColumns().add(tableCourse_RoomId);
		TableColumn<Map<String, String>, String> tableCourse_Capacity = new TableColumn<Map<String, String>, String>("Capacity");
		tableCourse_Capacity.setMinWidth("Capacity".length()*10);
		tableCourse_Capacity.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
			@Override
		    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
		        return new ReadOnlyStringWrapper(data.getValue().get("Capacity"));
		    }
		});	
		tableCourse.getColumns().add(tableCourse_Capacity);
		TableColumn<Map<String, String>, String> tableCourse_Status = new TableColumn<Map<String, String>, String>("Status");
		tableCourse_Status.setMinWidth("Status".length()*10);
		tableCourse_Status.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
			@Override
		    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
		        return new ReadOnlyStringWrapper(data.getValue().get("Status"));
		    }
		});	
		tableCourse.getColumns().add(tableCourse_Status);
		
		//table data
		ObservableList<Map<String, String>> dataCourse = FXCollections.observableArrayList();
		List<Course> rsCourse = EntityManager.getAllInstancesOf("Course");
		for (Course r : rsCourse) {
			//table entry
			Map<String, String> unit = new HashMap<String, String>();
			
			if (r.getId() != null)
				unit.put("Id", String.valueOf(r.getId()));
			else
				unit.put("Id", "");
			if (r.getName() != null)
				unit.put("Name", String.valueOf(r.getName()));
			else
				unit.put("Name", "");
			if (r.getTrainerId() != null)
				unit.put("TrainerId", String.valueOf(r.getTrainerId()));
			else
				unit.put("TrainerId", "");
			if (r.getDescription() != null)
				unit.put("Description", String.valueOf(r.getDescription()));
			else
				unit.put("Description", "");
			if (r.getRegisterStartTime() != null)
				unit.put("RegisterStartTime", String.valueOf(r.getRegisterStartTime()));
			else
				unit.put("RegisterStartTime", "");
			if (r.getRegisterEndTime() != null)
				unit.put("RegisterEndTime", String.valueOf(r.getRegisterEndTime()));
			else
				unit.put("RegisterEndTime", "");
			if (r.getEventStartTime() != null)
				unit.put("EventStartTime", String.valueOf(r.getEventStartTime()));
			else
				unit.put("EventStartTime", "");
			if (r.getEventEndTime() != null)
				unit.put("EventEndTime", String.valueOf(r.getEventEndTime()));
			else
				unit.put("EventEndTime", "");
			unit.put("Cost", String.valueOf(r.getCost()));
			if (r.getRoomId() != null)
				unit.put("RoomId", String.valueOf(r.getRoomId()));
			else
				unit.put("RoomId", "");
			unit.put("Capacity", String.valueOf(r.getCapacity()));
			unit.put("Status", String.valueOf(r.getStatus()));

			dataCourse.add(unit);
		}
		
		tableCourse.getSelectionModel().selectedIndexProperty().addListener(
							 (observable, oldValue, newValue) ->  { 
							 										 //get selected index
							 										 objectindex = tableCourse.getSelectionModel().getSelectedIndex();
							 			 				 			 System.out.println("select: " + objectindex);

							 			 				 			 //update association object information
							 			 				 			 if (objectindex != -1)
										 			 					 updateAssociation("Course", objectindex);
							 			 				 			 
							 			 				 		  });
		
		tableCourse.setItems(dataCourse);
		allObjectTables.put("Course", tableCourse);
		
		TableView<Map<String, String>> tableMember = new TableView<Map<String, String>>();

		//super entity attribute column
		TableColumn<Map<String, String>, String> tableMember_Id = new TableColumn<Map<String, String>, String>("Id");
		tableMember_Id.setMinWidth("Id".length()*10);
		tableMember_Id.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
			@Override
		    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
		        return new ReadOnlyStringWrapper(data.getValue().get("Id"));
		    }
		});	
		tableMember.getColumns().add(tableMember_Id);
		TableColumn<Map<String, String>, String> tableMember_Phone = new TableColumn<Map<String, String>, String>("Phone");
		tableMember_Phone.setMinWidth("Phone".length()*10);
		tableMember_Phone.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
			@Override
		    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
		        return new ReadOnlyStringWrapper(data.getValue().get("Phone"));
		    }
		});	
		tableMember.getColumns().add(tableMember_Phone);
		TableColumn<Map<String, String>, String> tableMember_Password = new TableColumn<Map<String, String>, String>("Password");
		tableMember_Password.setMinWidth("Password".length()*10);
		tableMember_Password.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
			@Override
		    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
		        return new ReadOnlyStringWrapper(data.getValue().get("Password"));
		    }
		});	
		tableMember.getColumns().add(tableMember_Password);
						
		//attributes table column
		TableColumn<Map<String, String>, String> tableMember_Name = new TableColumn<Map<String, String>, String>("Name");
		tableMember_Name.setMinWidth("Name".length()*10);
		tableMember_Name.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
			@Override
		    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
		        return new ReadOnlyStringWrapper(data.getValue().get("Name"));
		    }
		});	
		tableMember.getColumns().add(tableMember_Name);
		TableColumn<Map<String, String>, String> tableMember_Description = new TableColumn<Map<String, String>, String>("Description");
		tableMember_Description.setMinWidth("Description".length()*10);
		tableMember_Description.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
			@Override
		    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
		        return new ReadOnlyStringWrapper(data.getValue().get("Description"));
		    }
		});	
		tableMember.getColumns().add(tableMember_Description);
		TableColumn<Map<String, String>, String> tableMember_RegisterTime = new TableColumn<Map<String, String>, String>("RegisterTime");
		tableMember_RegisterTime.setMinWidth("RegisterTime".length()*10);
		tableMember_RegisterTime.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
			@Override
		    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
		        return new ReadOnlyStringWrapper(data.getValue().get("RegisterTime"));
		    }
		});	
		tableMember.getColumns().add(tableMember_RegisterTime);
		
		//table data
		ObservableList<Map<String, String>> dataMember = FXCollections.observableArrayList();
		List<Member> rsMember = EntityManager.getAllInstancesOf("Member");
		for (Member r : rsMember) {
			//table entry
			Map<String, String> unit = new HashMap<String, String>();
			if (r.getId() != null)
				unit.put("Id", String.valueOf(r.getId()));
			else
				unit.put("Id", "");
			if (r.getPhone() != null)
				unit.put("Phone", String.valueOf(r.getPhone()));
			else
				unit.put("Phone", "");
			if (r.getPassword() != null)
				unit.put("Password", String.valueOf(r.getPassword()));
			else
				unit.put("Password", "");
			
			if (r.getName() != null)
				unit.put("Name", String.valueOf(r.getName()));
			else
				unit.put("Name", "");
			if (r.getDescription() != null)
				unit.put("Description", String.valueOf(r.getDescription()));
			else
				unit.put("Description", "");
			if (r.getRegisterTime() != null)
				unit.put("RegisterTime", String.valueOf(r.getRegisterTime()));
			else
				unit.put("RegisterTime", "");

			dataMember.add(unit);
		}
		
		tableMember.getSelectionModel().selectedIndexProperty().addListener(
							 (observable, oldValue, newValue) ->  { 
							 										 //get selected index
							 										 objectindex = tableMember.getSelectionModel().getSelectedIndex();
							 			 				 			 System.out.println("select: " + objectindex);

							 			 				 			 //update association object information
							 			 				 			 if (objectindex != -1)
										 			 					 updateAssociation("Member", objectindex);
							 			 				 			 
							 			 				 		  });
		
		tableMember.setItems(dataMember);
		allObjectTables.put("Member", tableMember);
		
		TableView<Map<String, String>> tableTrainer = new TableView<Map<String, String>>();

		//super entity attribute column
		TableColumn<Map<String, String>, String> tableTrainer_Id = new TableColumn<Map<String, String>, String>("Id");
		tableTrainer_Id.setMinWidth("Id".length()*10);
		tableTrainer_Id.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
			@Override
		    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
		        return new ReadOnlyStringWrapper(data.getValue().get("Id"));
		    }
		});	
		tableTrainer.getColumns().add(tableTrainer_Id);
		TableColumn<Map<String, String>, String> tableTrainer_Phone = new TableColumn<Map<String, String>, String>("Phone");
		tableTrainer_Phone.setMinWidth("Phone".length()*10);
		tableTrainer_Phone.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
			@Override
		    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
		        return new ReadOnlyStringWrapper(data.getValue().get("Phone"));
		    }
		});	
		tableTrainer.getColumns().add(tableTrainer_Phone);
		TableColumn<Map<String, String>, String> tableTrainer_Password = new TableColumn<Map<String, String>, String>("Password");
		tableTrainer_Password.setMinWidth("Password".length()*10);
		tableTrainer_Password.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
			@Override
		    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
		        return new ReadOnlyStringWrapper(data.getValue().get("Password"));
		    }
		});	
		tableTrainer.getColumns().add(tableTrainer_Password);
						
		//attributes table column
		TableColumn<Map<String, String>, String> tableTrainer_Name = new TableColumn<Map<String, String>, String>("Name");
		tableTrainer_Name.setMinWidth("Name".length()*10);
		tableTrainer_Name.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
			@Override
		    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
		        return new ReadOnlyStringWrapper(data.getValue().get("Name"));
		    }
		});	
		tableTrainer.getColumns().add(tableTrainer_Name);
		TableColumn<Map<String, String>, String> tableTrainer_Description = new TableColumn<Map<String, String>, String>("Description");
		tableTrainer_Description.setMinWidth("Description".length()*10);
		tableTrainer_Description.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
			@Override
		    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
		        return new ReadOnlyStringWrapper(data.getValue().get("Description"));
		    }
		});	
		tableTrainer.getColumns().add(tableTrainer_Description);
		TableColumn<Map<String, String>, String> tableTrainer_RegisterTime = new TableColumn<Map<String, String>, String>("RegisterTime");
		tableTrainer_RegisterTime.setMinWidth("RegisterTime".length()*10);
		tableTrainer_RegisterTime.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
			@Override
		    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
		        return new ReadOnlyStringWrapper(data.getValue().get("RegisterTime"));
		    }
		});	
		tableTrainer.getColumns().add(tableTrainer_RegisterTime);
		
		//table data
		ObservableList<Map<String, String>> dataTrainer = FXCollections.observableArrayList();
		List<Trainer> rsTrainer = EntityManager.getAllInstancesOf("Trainer");
		for (Trainer r : rsTrainer) {
			//table entry
			Map<String, String> unit = new HashMap<String, String>();
			if (r.getId() != null)
				unit.put("Id", String.valueOf(r.getId()));
			else
				unit.put("Id", "");
			if (r.getPhone() != null)
				unit.put("Phone", String.valueOf(r.getPhone()));
			else
				unit.put("Phone", "");
			if (r.getPassword() != null)
				unit.put("Password", String.valueOf(r.getPassword()));
			else
				unit.put("Password", "");
			
			if (r.getName() != null)
				unit.put("Name", String.valueOf(r.getName()));
			else
				unit.put("Name", "");
			if (r.getDescription() != null)
				unit.put("Description", String.valueOf(r.getDescription()));
			else
				unit.put("Description", "");
			if (r.getRegisterTime() != null)
				unit.put("RegisterTime", String.valueOf(r.getRegisterTime()));
			else
				unit.put("RegisterTime", "");

			dataTrainer.add(unit);
		}
		
		tableTrainer.getSelectionModel().selectedIndexProperty().addListener(
							 (observable, oldValue, newValue) ->  { 
							 										 //get selected index
							 										 objectindex = tableTrainer.getSelectionModel().getSelectedIndex();
							 			 				 			 System.out.println("select: " + objectindex);

							 			 				 			 //update association object information
							 			 				 			 if (objectindex != -1)
										 			 					 updateAssociation("Trainer", objectindex);
							 			 				 			 
							 			 				 		  });
		
		tableTrainer.setItems(dataTrainer);
		allObjectTables.put("Trainer", tableTrainer);
		
		TableView<Map<String, String>> tableFrontDesk = new TableView<Map<String, String>>();

		//super entity attribute column
						
		//attributes table column
		TableColumn<Map<String, String>, String> tableFrontDesk_Id = new TableColumn<Map<String, String>, String>("Id");
		tableFrontDesk_Id.setMinWidth("Id".length()*10);
		tableFrontDesk_Id.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
			@Override
		    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
		        return new ReadOnlyStringWrapper(data.getValue().get("Id"));
		    }
		});	
		tableFrontDesk.getColumns().add(tableFrontDesk_Id);
		TableColumn<Map<String, String>, String> tableFrontDesk_Phone = new TableColumn<Map<String, String>, String>("Phone");
		tableFrontDesk_Phone.setMinWidth("Phone".length()*10);
		tableFrontDesk_Phone.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
			@Override
		    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
		        return new ReadOnlyStringWrapper(data.getValue().get("Phone"));
		    }
		});	
		tableFrontDesk.getColumns().add(tableFrontDesk_Phone);
		TableColumn<Map<String, String>, String> tableFrontDesk_Password = new TableColumn<Map<String, String>, String>("Password");
		tableFrontDesk_Password.setMinWidth("Password".length()*10);
		tableFrontDesk_Password.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
			@Override
		    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
		        return new ReadOnlyStringWrapper(data.getValue().get("Password"));
		    }
		});	
		tableFrontDesk.getColumns().add(tableFrontDesk_Password);
		
		//table data
		ObservableList<Map<String, String>> dataFrontDesk = FXCollections.observableArrayList();
		List<FrontDesk> rsFrontDesk = EntityManager.getAllInstancesOf("FrontDesk");
		for (FrontDesk r : rsFrontDesk) {
			//table entry
			Map<String, String> unit = new HashMap<String, String>();
			
			if (r.getId() != null)
				unit.put("Id", String.valueOf(r.getId()));
			else
				unit.put("Id", "");
			if (r.getPhone() != null)
				unit.put("Phone", String.valueOf(r.getPhone()));
			else
				unit.put("Phone", "");
			if (r.getPassword() != null)
				unit.put("Password", String.valueOf(r.getPassword()));
			else
				unit.put("Password", "");

			dataFrontDesk.add(unit);
		}
		
		tableFrontDesk.getSelectionModel().selectedIndexProperty().addListener(
							 (observable, oldValue, newValue) ->  { 
							 										 //get selected index
							 										 objectindex = tableFrontDesk.getSelectionModel().getSelectedIndex();
							 			 				 			 System.out.println("select: " + objectindex);

							 			 				 			 //update association object information
							 			 				 			 if (objectindex != -1)
										 			 					 updateAssociation("FrontDesk", objectindex);
							 			 				 			 
							 			 				 		  });
		
		tableFrontDesk.setItems(dataFrontDesk);
		allObjectTables.put("FrontDesk", tableFrontDesk);
		
		TableView<Map<String, String>> tableCourseRoom = new TableView<Map<String, String>>();

		//super entity attribute column
						
		//attributes table column
		TableColumn<Map<String, String>, String> tableCourseRoom_Id = new TableColumn<Map<String, String>, String>("Id");
		tableCourseRoom_Id.setMinWidth("Id".length()*10);
		tableCourseRoom_Id.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
			@Override
		    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
		        return new ReadOnlyStringWrapper(data.getValue().get("Id"));
		    }
		});	
		tableCourseRoom.getColumns().add(tableCourseRoom_Id);
		TableColumn<Map<String, String>, String> tableCourseRoom_Location = new TableColumn<Map<String, String>, String>("Location");
		tableCourseRoom_Location.setMinWidth("Location".length()*10);
		tableCourseRoom_Location.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
			@Override
		    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
		        return new ReadOnlyStringWrapper(data.getValue().get("Location"));
		    }
		});	
		tableCourseRoom.getColumns().add(tableCourseRoom_Location);
		TableColumn<Map<String, String>, String> tableCourseRoom_Capacity = new TableColumn<Map<String, String>, String>("Capacity");
		tableCourseRoom_Capacity.setMinWidth("Capacity".length()*10);
		tableCourseRoom_Capacity.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
			@Override
		    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
		        return new ReadOnlyStringWrapper(data.getValue().get("Capacity"));
		    }
		});	
		tableCourseRoom.getColumns().add(tableCourseRoom_Capacity);
		
		//table data
		ObservableList<Map<String, String>> dataCourseRoom = FXCollections.observableArrayList();
		List<CourseRoom> rsCourseRoom = EntityManager.getAllInstancesOf("CourseRoom");
		for (CourseRoom r : rsCourseRoom) {
			//table entry
			Map<String, String> unit = new HashMap<String, String>();
			
			if (r.getId() != null)
				unit.put("Id", String.valueOf(r.getId()));
			else
				unit.put("Id", "");
			if (r.getLocation() != null)
				unit.put("Location", String.valueOf(r.getLocation()));
			else
				unit.put("Location", "");
			unit.put("Capacity", String.valueOf(r.getCapacity()));

			dataCourseRoom.add(unit);
		}
		
		tableCourseRoom.getSelectionModel().selectedIndexProperty().addListener(
							 (observable, oldValue, newValue) ->  { 
							 										 //get selected index
							 										 objectindex = tableCourseRoom.getSelectionModel().getSelectedIndex();
							 			 				 			 System.out.println("select: " + objectindex);

							 			 				 			 //update association object information
							 			 				 			 if (objectindex != -1)
										 			 					 updateAssociation("CourseRoom", objectindex);
							 			 				 			 
							 			 				 		  });
		
		tableCourseRoom.setItems(dataCourseRoom);
		allObjectTables.put("CourseRoom", tableCourseRoom);
		
		TableView<Map<String, String>> tableCourseNotification = new TableView<Map<String, String>>();

		//super entity attribute column
						
		//attributes table column
		TableColumn<Map<String, String>, String> tableCourseNotification_Id = new TableColumn<Map<String, String>, String>("Id");
		tableCourseNotification_Id.setMinWidth("Id".length()*10);
		tableCourseNotification_Id.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
			@Override
		    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
		        return new ReadOnlyStringWrapper(data.getValue().get("Id"));
		    }
		});	
		tableCourseNotification.getColumns().add(tableCourseNotification_Id);
		TableColumn<Map<String, String>, String> tableCourseNotification_UserId = new TableColumn<Map<String, String>, String>("UserId");
		tableCourseNotification_UserId.setMinWidth("UserId".length()*10);
		tableCourseNotification_UserId.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
			@Override
		    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
		        return new ReadOnlyStringWrapper(data.getValue().get("UserId"));
		    }
		});	
		tableCourseNotification.getColumns().add(tableCourseNotification_UserId);
		TableColumn<Map<String, String>, String> tableCourseNotification_Message = new TableColumn<Map<String, String>, String>("Message");
		tableCourseNotification_Message.setMinWidth("Message".length()*10);
		tableCourseNotification_Message.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
			@Override
		    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
		        return new ReadOnlyStringWrapper(data.getValue().get("Message"));
		    }
		});	
		tableCourseNotification.getColumns().add(tableCourseNotification_Message);
		
		//table data
		ObservableList<Map<String, String>> dataCourseNotification = FXCollections.observableArrayList();
		List<CourseNotification> rsCourseNotification = EntityManager.getAllInstancesOf("CourseNotification");
		for (CourseNotification r : rsCourseNotification) {
			//table entry
			Map<String, String> unit = new HashMap<String, String>();
			
			if (r.getId() != null)
				unit.put("Id", String.valueOf(r.getId()));
			else
				unit.put("Id", "");
			if (r.getUserId() != null)
				unit.put("UserId", String.valueOf(r.getUserId()));
			else
				unit.put("UserId", "");
			if (r.getMessage() != null)
				unit.put("Message", String.valueOf(r.getMessage()));
			else
				unit.put("Message", "");

			dataCourseNotification.add(unit);
		}
		
		tableCourseNotification.getSelectionModel().selectedIndexProperty().addListener(
							 (observable, oldValue, newValue) ->  { 
							 										 //get selected index
							 										 objectindex = tableCourseNotification.getSelectionModel().getSelectedIndex();
							 			 				 			 System.out.println("select: " + objectindex);

							 			 				 			 //update association object information
							 			 				 			 if (objectindex != -1)
										 			 					 updateAssociation("CourseNotification", objectindex);
							 			 				 			 
							 			 				 		  });
		
		tableCourseNotification.setItems(dataCourseNotification);
		allObjectTables.put("CourseNotification", tableCourseNotification);
		
		TableView<Map<String, String>> tableCourseRecord = new TableView<Map<String, String>>();

		//super entity attribute column
						
		//attributes table column
		TableColumn<Map<String, String>, String> tableCourseRecord_Id = new TableColumn<Map<String, String>, String>("Id");
		tableCourseRecord_Id.setMinWidth("Id".length()*10);
		tableCourseRecord_Id.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
			@Override
		    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
		        return new ReadOnlyStringWrapper(data.getValue().get("Id"));
		    }
		});	
		tableCourseRecord.getColumns().add(tableCourseRecord_Id);
		TableColumn<Map<String, String>, String> tableCourseRecord_UserId = new TableColumn<Map<String, String>, String>("UserId");
		tableCourseRecord_UserId.setMinWidth("UserId".length()*10);
		tableCourseRecord_UserId.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
			@Override
		    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
		        return new ReadOnlyStringWrapper(data.getValue().get("UserId"));
		    }
		});	
		tableCourseRecord.getColumns().add(tableCourseRecord_UserId);
		TableColumn<Map<String, String>, String> tableCourseRecord_CourseId = new TableColumn<Map<String, String>, String>("CourseId");
		tableCourseRecord_CourseId.setMinWidth("CourseId".length()*10);
		tableCourseRecord_CourseId.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
			@Override
		    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
		        return new ReadOnlyStringWrapper(data.getValue().get("CourseId"));
		    }
		});	
		tableCourseRecord.getColumns().add(tableCourseRecord_CourseId);
		TableColumn<Map<String, String>, String> tableCourseRecord_Status = new TableColumn<Map<String, String>, String>("Status");
		tableCourseRecord_Status.setMinWidth("Status".length()*10);
		tableCourseRecord_Status.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
			@Override
		    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
		        return new ReadOnlyStringWrapper(data.getValue().get("Status"));
		    }
		});	
		tableCourseRecord.getColumns().add(tableCourseRecord_Status);
		
		//table data
		ObservableList<Map<String, String>> dataCourseRecord = FXCollections.observableArrayList();
		List<CourseRecord> rsCourseRecord = EntityManager.getAllInstancesOf("CourseRecord");
		for (CourseRecord r : rsCourseRecord) {
			//table entry
			Map<String, String> unit = new HashMap<String, String>();
			
			if (r.getId() != null)
				unit.put("Id", String.valueOf(r.getId()));
			else
				unit.put("Id", "");
			if (r.getUserId() != null)
				unit.put("UserId", String.valueOf(r.getUserId()));
			else
				unit.put("UserId", "");
			if (r.getCourseId() != null)
				unit.put("CourseId", String.valueOf(r.getCourseId()));
			else
				unit.put("CourseId", "");
			unit.put("Status", String.valueOf(r.getStatus()));

			dataCourseRecord.add(unit);
		}
		
		tableCourseRecord.getSelectionModel().selectedIndexProperty().addListener(
							 (observable, oldValue, newValue) ->  { 
							 										 //get selected index
							 										 objectindex = tableCourseRecord.getSelectionModel().getSelectedIndex();
							 			 				 			 System.out.println("select: " + objectindex);

							 			 				 			 //update association object information
							 			 				 			 if (objectindex != -1)
										 			 					 updateAssociation("CourseRecord", objectindex);
							 			 				 			 
							 			 				 		  });
		
		tableCourseRecord.setItems(dataCourseRecord);
		allObjectTables.put("CourseRecord", tableCourseRecord);
		
		TableView<Map<String, String>> tableCoursePayment = new TableView<Map<String, String>>();

		//super entity attribute column
						
		//attributes table column
		TableColumn<Map<String, String>, String> tableCoursePayment_Id = new TableColumn<Map<String, String>, String>("Id");
		tableCoursePayment_Id.setMinWidth("Id".length()*10);
		tableCoursePayment_Id.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
			@Override
		    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
		        return new ReadOnlyStringWrapper(data.getValue().get("Id"));
		    }
		});	
		tableCoursePayment.getColumns().add(tableCoursePayment_Id);
		TableColumn<Map<String, String>, String> tableCoursePayment_UserId = new TableColumn<Map<String, String>, String>("UserId");
		tableCoursePayment_UserId.setMinWidth("UserId".length()*10);
		tableCoursePayment_UserId.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
			@Override
		    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
		        return new ReadOnlyStringWrapper(data.getValue().get("UserId"));
		    }
		});	
		tableCoursePayment.getColumns().add(tableCoursePayment_UserId);
		TableColumn<Map<String, String>, String> tableCoursePayment_CourseId = new TableColumn<Map<String, String>, String>("CourseId");
		tableCoursePayment_CourseId.setMinWidth("CourseId".length()*10);
		tableCoursePayment_CourseId.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
			@Override
		    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
		        return new ReadOnlyStringWrapper(data.getValue().get("CourseId"));
		    }
		});	
		tableCoursePayment.getColumns().add(tableCoursePayment_CourseId);
		TableColumn<Map<String, String>, String> tableCoursePayment_Price = new TableColumn<Map<String, String>, String>("Price");
		tableCoursePayment_Price.setMinWidth("Price".length()*10);
		tableCoursePayment_Price.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
			@Override
		    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
		        return new ReadOnlyStringWrapper(data.getValue().get("Price"));
		    }
		});	
		tableCoursePayment.getColumns().add(tableCoursePayment_Price);
		TableColumn<Map<String, String>, String> tableCoursePayment_PayTime = new TableColumn<Map<String, String>, String>("PayTime");
		tableCoursePayment_PayTime.setMinWidth("PayTime".length()*10);
		tableCoursePayment_PayTime.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
			@Override
		    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
		        return new ReadOnlyStringWrapper(data.getValue().get("PayTime"));
		    }
		});	
		tableCoursePayment.getColumns().add(tableCoursePayment_PayTime);
		TableColumn<Map<String, String>, String> tableCoursePayment_PayStatus = new TableColumn<Map<String, String>, String>("PayStatus");
		tableCoursePayment_PayStatus.setMinWidth("PayStatus".length()*10);
		tableCoursePayment_PayStatus.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
			@Override
		    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
		        return new ReadOnlyStringWrapper(data.getValue().get("PayStatus"));
		    }
		});	
		tableCoursePayment.getColumns().add(tableCoursePayment_PayStatus);
		
		//table data
		ObservableList<Map<String, String>> dataCoursePayment = FXCollections.observableArrayList();
		List<CoursePayment> rsCoursePayment = EntityManager.getAllInstancesOf("CoursePayment");
		for (CoursePayment r : rsCoursePayment) {
			//table entry
			Map<String, String> unit = new HashMap<String, String>();
			
			if (r.getId() != null)
				unit.put("Id", String.valueOf(r.getId()));
			else
				unit.put("Id", "");
			if (r.getUserId() != null)
				unit.put("UserId", String.valueOf(r.getUserId()));
			else
				unit.put("UserId", "");
			if (r.getCourseId() != null)
				unit.put("CourseId", String.valueOf(r.getCourseId()));
			else
				unit.put("CourseId", "");
			unit.put("Price", String.valueOf(r.getPrice()));
			if (r.getPayTime() != null)
				unit.put("PayTime", String.valueOf(r.getPayTime()));
			else
				unit.put("PayTime", "");
			unit.put("PayStatus", String.valueOf(r.getPayStatus()));

			dataCoursePayment.add(unit);
		}
		
		tableCoursePayment.getSelectionModel().selectedIndexProperty().addListener(
							 (observable, oldValue, newValue) ->  { 
							 										 //get selected index
							 										 objectindex = tableCoursePayment.getSelectionModel().getSelectedIndex();
							 			 				 			 System.out.println("select: " + objectindex);

							 			 				 			 //update association object information
							 			 				 			 if (objectindex != -1)
										 			 					 updateAssociation("CoursePayment", objectindex);
							 			 				 			 
							 			 				 		  });
		
		tableCoursePayment.setItems(dataCoursePayment);
		allObjectTables.put("CoursePayment", tableCoursePayment);
		

		
	}
	
	/* 
	* update all object tables with sub dataset
	*/ 
	public void updateCourseTable(List<Course> rsCourse) {
			ObservableList<Map<String, String>> dataCourse = FXCollections.observableArrayList();
			for (Course r : rsCourse) {
				Map<String, String> unit = new HashMap<String, String>();
				
				
				if (r.getId() != null)
					unit.put("Id", String.valueOf(r.getId()));
				else
					unit.put("Id", "");
				if (r.getName() != null)
					unit.put("Name", String.valueOf(r.getName()));
				else
					unit.put("Name", "");
				if (r.getTrainerId() != null)
					unit.put("TrainerId", String.valueOf(r.getTrainerId()));
				else
					unit.put("TrainerId", "");
				if (r.getDescription() != null)
					unit.put("Description", String.valueOf(r.getDescription()));
				else
					unit.put("Description", "");
				if (r.getRegisterStartTime() != null)
					unit.put("RegisterStartTime", String.valueOf(r.getRegisterStartTime()));
				else
					unit.put("RegisterStartTime", "");
				if (r.getRegisterEndTime() != null)
					unit.put("RegisterEndTime", String.valueOf(r.getRegisterEndTime()));
				else
					unit.put("RegisterEndTime", "");
				if (r.getEventStartTime() != null)
					unit.put("EventStartTime", String.valueOf(r.getEventStartTime()));
				else
					unit.put("EventStartTime", "");
				if (r.getEventEndTime() != null)
					unit.put("EventEndTime", String.valueOf(r.getEventEndTime()));
				else
					unit.put("EventEndTime", "");
				unit.put("Cost", String.valueOf(r.getCost()));
				if (r.getRoomId() != null)
					unit.put("RoomId", String.valueOf(r.getRoomId()));
				else
					unit.put("RoomId", "");
				unit.put("Capacity", String.valueOf(r.getCapacity()));
				unit.put("Status", String.valueOf(r.getStatus()));
				dataCourse.add(unit);
			}
			
			allObjectTables.get("Course").setItems(dataCourse);
	}
	public void updateMemberTable(List<Member> rsMember) {
			ObservableList<Map<String, String>> dataMember = FXCollections.observableArrayList();
			for (Member r : rsMember) {
				Map<String, String> unit = new HashMap<String, String>();
				
				if (r.getId() != null)
					unit.put("Id", String.valueOf(r.getId()));
				else
					unit.put("Id", "");
				if (r.getPhone() != null)
					unit.put("Phone", String.valueOf(r.getPhone()));
				else
					unit.put("Phone", "");
				if (r.getPassword() != null)
					unit.put("Password", String.valueOf(r.getPassword()));
				else
					unit.put("Password", "");
				
				if (r.getName() != null)
					unit.put("Name", String.valueOf(r.getName()));
				else
					unit.put("Name", "");
				if (r.getDescription() != null)
					unit.put("Description", String.valueOf(r.getDescription()));
				else
					unit.put("Description", "");
				if (r.getRegisterTime() != null)
					unit.put("RegisterTime", String.valueOf(r.getRegisterTime()));
				else
					unit.put("RegisterTime", "");
				dataMember.add(unit);
			}
			
			allObjectTables.get("Member").setItems(dataMember);
	}
	public void updateTrainerTable(List<Trainer> rsTrainer) {
			ObservableList<Map<String, String>> dataTrainer = FXCollections.observableArrayList();
			for (Trainer r : rsTrainer) {
				Map<String, String> unit = new HashMap<String, String>();
				
				if (r.getId() != null)
					unit.put("Id", String.valueOf(r.getId()));
				else
					unit.put("Id", "");
				if (r.getPhone() != null)
					unit.put("Phone", String.valueOf(r.getPhone()));
				else
					unit.put("Phone", "");
				if (r.getPassword() != null)
					unit.put("Password", String.valueOf(r.getPassword()));
				else
					unit.put("Password", "");
				
				if (r.getName() != null)
					unit.put("Name", String.valueOf(r.getName()));
				else
					unit.put("Name", "");
				if (r.getDescription() != null)
					unit.put("Description", String.valueOf(r.getDescription()));
				else
					unit.put("Description", "");
				if (r.getRegisterTime() != null)
					unit.put("RegisterTime", String.valueOf(r.getRegisterTime()));
				else
					unit.put("RegisterTime", "");
				dataTrainer.add(unit);
			}
			
			allObjectTables.get("Trainer").setItems(dataTrainer);
	}
	public void updateFrontDeskTable(List<FrontDesk> rsFrontDesk) {
			ObservableList<Map<String, String>> dataFrontDesk = FXCollections.observableArrayList();
			for (FrontDesk r : rsFrontDesk) {
				Map<String, String> unit = new HashMap<String, String>();
				
				
				if (r.getId() != null)
					unit.put("Id", String.valueOf(r.getId()));
				else
					unit.put("Id", "");
				if (r.getPhone() != null)
					unit.put("Phone", String.valueOf(r.getPhone()));
				else
					unit.put("Phone", "");
				if (r.getPassword() != null)
					unit.put("Password", String.valueOf(r.getPassword()));
				else
					unit.put("Password", "");
				dataFrontDesk.add(unit);
			}
			
			allObjectTables.get("FrontDesk").setItems(dataFrontDesk);
	}
	public void updateCourseRoomTable(List<CourseRoom> rsCourseRoom) {
			ObservableList<Map<String, String>> dataCourseRoom = FXCollections.observableArrayList();
			for (CourseRoom r : rsCourseRoom) {
				Map<String, String> unit = new HashMap<String, String>();
				
				
				if (r.getId() != null)
					unit.put("Id", String.valueOf(r.getId()));
				else
					unit.put("Id", "");
				if (r.getLocation() != null)
					unit.put("Location", String.valueOf(r.getLocation()));
				else
					unit.put("Location", "");
				unit.put("Capacity", String.valueOf(r.getCapacity()));
				dataCourseRoom.add(unit);
			}
			
			allObjectTables.get("CourseRoom").setItems(dataCourseRoom);
	}
	public void updateCourseNotificationTable(List<CourseNotification> rsCourseNotification) {
			ObservableList<Map<String, String>> dataCourseNotification = FXCollections.observableArrayList();
			for (CourseNotification r : rsCourseNotification) {
				Map<String, String> unit = new HashMap<String, String>();
				
				
				if (r.getId() != null)
					unit.put("Id", String.valueOf(r.getId()));
				else
					unit.put("Id", "");
				if (r.getUserId() != null)
					unit.put("UserId", String.valueOf(r.getUserId()));
				else
					unit.put("UserId", "");
				if (r.getMessage() != null)
					unit.put("Message", String.valueOf(r.getMessage()));
				else
					unit.put("Message", "");
				dataCourseNotification.add(unit);
			}
			
			allObjectTables.get("CourseNotification").setItems(dataCourseNotification);
	}
	public void updateCourseRecordTable(List<CourseRecord> rsCourseRecord) {
			ObservableList<Map<String, String>> dataCourseRecord = FXCollections.observableArrayList();
			for (CourseRecord r : rsCourseRecord) {
				Map<String, String> unit = new HashMap<String, String>();
				
				
				if (r.getId() != null)
					unit.put("Id", String.valueOf(r.getId()));
				else
					unit.put("Id", "");
				if (r.getUserId() != null)
					unit.put("UserId", String.valueOf(r.getUserId()));
				else
					unit.put("UserId", "");
				if (r.getCourseId() != null)
					unit.put("CourseId", String.valueOf(r.getCourseId()));
				else
					unit.put("CourseId", "");
				unit.put("Status", String.valueOf(r.getStatus()));
				dataCourseRecord.add(unit);
			}
			
			allObjectTables.get("CourseRecord").setItems(dataCourseRecord);
	}
	public void updateCoursePaymentTable(List<CoursePayment> rsCoursePayment) {
			ObservableList<Map<String, String>> dataCoursePayment = FXCollections.observableArrayList();
			for (CoursePayment r : rsCoursePayment) {
				Map<String, String> unit = new HashMap<String, String>();
				
				
				if (r.getId() != null)
					unit.put("Id", String.valueOf(r.getId()));
				else
					unit.put("Id", "");
				if (r.getUserId() != null)
					unit.put("UserId", String.valueOf(r.getUserId()));
				else
					unit.put("UserId", "");
				if (r.getCourseId() != null)
					unit.put("CourseId", String.valueOf(r.getCourseId()));
				else
					unit.put("CourseId", "");
				unit.put("Price", String.valueOf(r.getPrice()));
				if (r.getPayTime() != null)
					unit.put("PayTime", String.valueOf(r.getPayTime()));
				else
					unit.put("PayTime", "");
				unit.put("PayStatus", String.valueOf(r.getPayStatus()));
				dataCoursePayment.add(unit);
			}
			
			allObjectTables.get("CoursePayment").setItems(dataCoursePayment);
	}
	
	/* 
	* update all object tables
	*/ 
	public void updateCourseTable() {
			ObservableList<Map<String, String>> dataCourse = FXCollections.observableArrayList();
			List<Course> rsCourse = EntityManager.getAllInstancesOf("Course");
			for (Course r : rsCourse) {
				Map<String, String> unit = new HashMap<String, String>();


				if (r.getId() != null)
					unit.put("Id", String.valueOf(r.getId()));
				else
					unit.put("Id", "");
				if (r.getName() != null)
					unit.put("Name", String.valueOf(r.getName()));
				else
					unit.put("Name", "");
				if (r.getTrainerId() != null)
					unit.put("TrainerId", String.valueOf(r.getTrainerId()));
				else
					unit.put("TrainerId", "");
				if (r.getDescription() != null)
					unit.put("Description", String.valueOf(r.getDescription()));
				else
					unit.put("Description", "");
				if (r.getRegisterStartTime() != null)
					unit.put("RegisterStartTime", String.valueOf(r.getRegisterStartTime()));
				else
					unit.put("RegisterStartTime", "");
				if (r.getRegisterEndTime() != null)
					unit.put("RegisterEndTime", String.valueOf(r.getRegisterEndTime()));
				else
					unit.put("RegisterEndTime", "");
				if (r.getEventStartTime() != null)
					unit.put("EventStartTime", String.valueOf(r.getEventStartTime()));
				else
					unit.put("EventStartTime", "");
				if (r.getEventEndTime() != null)
					unit.put("EventEndTime", String.valueOf(r.getEventEndTime()));
				else
					unit.put("EventEndTime", "");
				unit.put("Cost", String.valueOf(r.getCost()));
				if (r.getRoomId() != null)
					unit.put("RoomId", String.valueOf(r.getRoomId()));
				else
					unit.put("RoomId", "");
				unit.put("Capacity", String.valueOf(r.getCapacity()));
				unit.put("Status", String.valueOf(r.getStatus()));
				dataCourse.add(unit);
			}
			
			allObjectTables.get("Course").setItems(dataCourse);
	}
	public void updateMemberTable() {
			ObservableList<Map<String, String>> dataMember = FXCollections.observableArrayList();
			List<Member> rsMember = EntityManager.getAllInstancesOf("Member");
			for (Member r : rsMember) {
				Map<String, String> unit = new HashMap<String, String>();

				if (r.getId() != null)
					unit.put("Id", String.valueOf(r.getId()));
				else
					unit.put("Id", "");
				if (r.getPhone() != null)
					unit.put("Phone", String.valueOf(r.getPhone()));
				else
					unit.put("Phone", "");
				if (r.getPassword() != null)
					unit.put("Password", String.valueOf(r.getPassword()));
				else
					unit.put("Password", "");

				if (r.getName() != null)
					unit.put("Name", String.valueOf(r.getName()));
				else
					unit.put("Name", "");
				if (r.getDescription() != null)
					unit.put("Description", String.valueOf(r.getDescription()));
				else
					unit.put("Description", "");
				if (r.getRegisterTime() != null)
					unit.put("RegisterTime", String.valueOf(r.getRegisterTime()));
				else
					unit.put("RegisterTime", "");
				dataMember.add(unit);
			}
			
			allObjectTables.get("Member").setItems(dataMember);
	}
	public void updateTrainerTable() {
			ObservableList<Map<String, String>> dataTrainer = FXCollections.observableArrayList();
			List<Trainer> rsTrainer = EntityManager.getAllInstancesOf("Trainer");
			for (Trainer r : rsTrainer) {
				Map<String, String> unit = new HashMap<String, String>();

				if (r.getId() != null)
					unit.put("Id", String.valueOf(r.getId()));
				else
					unit.put("Id", "");
				if (r.getPhone() != null)
					unit.put("Phone", String.valueOf(r.getPhone()));
				else
					unit.put("Phone", "");
				if (r.getPassword() != null)
					unit.put("Password", String.valueOf(r.getPassword()));
				else
					unit.put("Password", "");

				if (r.getName() != null)
					unit.put("Name", String.valueOf(r.getName()));
				else
					unit.put("Name", "");
				if (r.getDescription() != null)
					unit.put("Description", String.valueOf(r.getDescription()));
				else
					unit.put("Description", "");
				if (r.getRegisterTime() != null)
					unit.put("RegisterTime", String.valueOf(r.getRegisterTime()));
				else
					unit.put("RegisterTime", "");
				dataTrainer.add(unit);
			}
			
			allObjectTables.get("Trainer").setItems(dataTrainer);
	}
	public void updateFrontDeskTable() {
			ObservableList<Map<String, String>> dataFrontDesk = FXCollections.observableArrayList();
			List<FrontDesk> rsFrontDesk = EntityManager.getAllInstancesOf("FrontDesk");
			for (FrontDesk r : rsFrontDesk) {
				Map<String, String> unit = new HashMap<String, String>();


				if (r.getId() != null)
					unit.put("Id", String.valueOf(r.getId()));
				else
					unit.put("Id", "");
				if (r.getPhone() != null)
					unit.put("Phone", String.valueOf(r.getPhone()));
				else
					unit.put("Phone", "");
				if (r.getPassword() != null)
					unit.put("Password", String.valueOf(r.getPassword()));
				else
					unit.put("Password", "");
				dataFrontDesk.add(unit);
			}
			
			allObjectTables.get("FrontDesk").setItems(dataFrontDesk);
	}
	public void updateCourseRoomTable() {
			ObservableList<Map<String, String>> dataCourseRoom = FXCollections.observableArrayList();
			List<CourseRoom> rsCourseRoom = EntityManager.getAllInstancesOf("CourseRoom");
			for (CourseRoom r : rsCourseRoom) {
				Map<String, String> unit = new HashMap<String, String>();


				if (r.getId() != null)
					unit.put("Id", String.valueOf(r.getId()));
				else
					unit.put("Id", "");
				if (r.getLocation() != null)
					unit.put("Location", String.valueOf(r.getLocation()));
				else
					unit.put("Location", "");
				unit.put("Capacity", String.valueOf(r.getCapacity()));
				dataCourseRoom.add(unit);
			}
			
			allObjectTables.get("CourseRoom").setItems(dataCourseRoom);
	}
	public void updateCourseNotificationTable() {
			ObservableList<Map<String, String>> dataCourseNotification = FXCollections.observableArrayList();
			List<CourseNotification> rsCourseNotification = EntityManager.getAllInstancesOf("CourseNotification");
			for (CourseNotification r : rsCourseNotification) {
				Map<String, String> unit = new HashMap<String, String>();


				if (r.getId() != null)
					unit.put("Id", String.valueOf(r.getId()));
				else
					unit.put("Id", "");
				if (r.getUserId() != null)
					unit.put("UserId", String.valueOf(r.getUserId()));
				else
					unit.put("UserId", "");
				if (r.getMessage() != null)
					unit.put("Message", String.valueOf(r.getMessage()));
				else
					unit.put("Message", "");
				dataCourseNotification.add(unit);
			}
			
			allObjectTables.get("CourseNotification").setItems(dataCourseNotification);
	}
	public void updateCourseRecordTable() {
			ObservableList<Map<String, String>> dataCourseRecord = FXCollections.observableArrayList();
			List<CourseRecord> rsCourseRecord = EntityManager.getAllInstancesOf("CourseRecord");
			for (CourseRecord r : rsCourseRecord) {
				Map<String, String> unit = new HashMap<String, String>();


				if (r.getId() != null)
					unit.put("Id", String.valueOf(r.getId()));
				else
					unit.put("Id", "");
				if (r.getUserId() != null)
					unit.put("UserId", String.valueOf(r.getUserId()));
				else
					unit.put("UserId", "");
				if (r.getCourseId() != null)
					unit.put("CourseId", String.valueOf(r.getCourseId()));
				else
					unit.put("CourseId", "");
				unit.put("Status", String.valueOf(r.getStatus()));
				dataCourseRecord.add(unit);
			}
			
			allObjectTables.get("CourseRecord").setItems(dataCourseRecord);
	}
	public void updateCoursePaymentTable() {
			ObservableList<Map<String, String>> dataCoursePayment = FXCollections.observableArrayList();
			List<CoursePayment> rsCoursePayment = EntityManager.getAllInstancesOf("CoursePayment");
			for (CoursePayment r : rsCoursePayment) {
				Map<String, String> unit = new HashMap<String, String>();


				if (r.getId() != null)
					unit.put("Id", String.valueOf(r.getId()));
				else
					unit.put("Id", "");
				if (r.getUserId() != null)
					unit.put("UserId", String.valueOf(r.getUserId()));
				else
					unit.put("UserId", "");
				if (r.getCourseId() != null)
					unit.put("CourseId", String.valueOf(r.getCourseId()));
				else
					unit.put("CourseId", "");
				unit.put("Price", String.valueOf(r.getPrice()));
				if (r.getPayTime() != null)
					unit.put("PayTime", String.valueOf(r.getPayTime()));
				else
					unit.put("PayTime", "");
				unit.put("PayStatus", String.valueOf(r.getPayStatus()));
				dataCoursePayment.add(unit);
			}
			
			allObjectTables.get("CoursePayment").setItems(dataCoursePayment);
	}
	
	public void classStatisicBingding() {
	
		 classInfodata = FXCollections.observableArrayList();
	 	 course = new ClassInfo("Course", EntityManager.getAllInstancesOf("Course").size());
	 	 classInfodata.add(course);
	 	 member = new ClassInfo("Member", EntityManager.getAllInstancesOf("Member").size());
	 	 classInfodata.add(member);
	 	 trainer = new ClassInfo("Trainer", EntityManager.getAllInstancesOf("Trainer").size());
	 	 classInfodata.add(trainer);
	 	 frontdesk = new ClassInfo("FrontDesk", EntityManager.getAllInstancesOf("FrontDesk").size());
	 	 classInfodata.add(frontdesk);
	 	 courseroom = new ClassInfo("CourseRoom", EntityManager.getAllInstancesOf("CourseRoom").size());
	 	 classInfodata.add(courseroom);
	 	 coursenotification = new ClassInfo("CourseNotification", EntityManager.getAllInstancesOf("CourseNotification").size());
	 	 classInfodata.add(coursenotification);
	 	 courserecord = new ClassInfo("CourseRecord", EntityManager.getAllInstancesOf("CourseRecord").size());
	 	 classInfodata.add(courserecord);
	 	 coursepayment = new ClassInfo("CoursePayment", EntityManager.getAllInstancesOf("CoursePayment").size());
	 	 classInfodata.add(coursepayment);
	 	 
		 class_statisic.setItems(classInfodata);
		 
		 //Class Statisic Binding
		 class_statisic.getSelectionModel().selectedItemProperty().addListener(
				 (observable, oldValue, newValue) ->  { 
				 										 //no selected object in table
				 										 objectindex = -1;
				 										 
				 										 //get lastest data, reflect updateTableData method
				 										 try {
												 			 Method updateob = this.getClass().getMethod("update" + newValue.getName() + "Table", null);
												 			 updateob.invoke(this);			 
												 		 } catch (Exception e) {
												 		 	 e.printStackTrace();
												 		 }		 										 
				 	
				 										 //show object table
				 			 				 			 TableView obs = allObjectTables.get(newValue.getName());
				 			 				 			 if (obs != null) {
				 			 				 				object_statics.setContent(obs);
				 			 				 				object_statics.setText("All Objects " + newValue.getName() + ":");
				 			 				 			 }
				 			 				 			 
				 			 				 			 //update association information
							 			 				 updateAssociation(newValue.getName());
				 			 				 			 
				 			 				 			 //show association information
				 			 				 			 ObservableList<AssociationInfo> asso = allassociationData.get(newValue.getName());
				 			 				 			 if (asso != null) {
				 			 				 			 	association_statisic.setItems(asso);
				 			 				 			 }
				 			 				 		  });
	}
	
	public void classStatisticUpdate() {
	 	 course.setNumber(EntityManager.getAllInstancesOf("Course").size());
	 	 member.setNumber(EntityManager.getAllInstancesOf("Member").size());
	 	 trainer.setNumber(EntityManager.getAllInstancesOf("Trainer").size());
	 	 frontdesk.setNumber(EntityManager.getAllInstancesOf("FrontDesk").size());
	 	 courseroom.setNumber(EntityManager.getAllInstancesOf("CourseRoom").size());
	 	 coursenotification.setNumber(EntityManager.getAllInstancesOf("CourseNotification").size());
	 	 courserecord.setNumber(EntityManager.getAllInstancesOf("CourseRecord").size());
	 	 coursepayment.setNumber(EntityManager.getAllInstancesOf("CoursePayment").size());
		
	}
	
	/**
	 * association binding
	 */
	public void associationStatisicBingding() {
		
		allassociationData = new HashMap<String, ObservableList<AssociationInfo>>();
		
		ObservableList<AssociationInfo> Course_association_data = FXCollections.observableArrayList();
		AssociationInfo Course_associatition_CoursetoCourseRoom = new AssociationInfo("Course", "CourseRoom", "CoursetoCourseRoom", false);
		Course_association_data.add(Course_associatition_CoursetoCourseRoom);
		AssociationInfo Course_associatition_CoursetoCourseNotification = new AssociationInfo("Course", "CourseNotification", "CoursetoCourseNotification", true);
		Course_association_data.add(Course_associatition_CoursetoCourseNotification);
		AssociationInfo Course_associatition_CoursetoCoursePayment = new AssociationInfo("Course", "CoursePayment", "CoursetoCoursePayment", true);
		Course_association_data.add(Course_associatition_CoursetoCoursePayment);
		AssociationInfo Course_associatition_CoursetoCourseRecord = new AssociationInfo("Course", "CourseRecord", "CoursetoCourseRecord", true);
		Course_association_data.add(Course_associatition_CoursetoCourseRecord);
		
		allassociationData.put("Course", Course_association_data);
		
		ObservableList<AssociationInfo> Member_association_data = FXCollections.observableArrayList();
		AssociationInfo Member_associatition_MembertoCourse = new AssociationInfo("Member", "Course", "MembertoCourse", true);
		Member_association_data.add(Member_associatition_MembertoCourse);
		AssociationInfo Member_associatition_MembertoCourseNotification = new AssociationInfo("Member", "CourseNotification", "MembertoCourseNotification", true);
		Member_association_data.add(Member_associatition_MembertoCourseNotification);
		AssociationInfo Member_associatition_MembertoCourseRecord = new AssociationInfo("Member", "CourseRecord", "MembertoCourseRecord", true);
		Member_association_data.add(Member_associatition_MembertoCourseRecord);
		
		allassociationData.put("Member", Member_association_data);
		
		ObservableList<AssociationInfo> Trainer_association_data = FXCollections.observableArrayList();
		AssociationInfo Trainer_associatition_TrainertoCourse = new AssociationInfo("Trainer", "Course", "TrainertoCourse", true);
		Trainer_association_data.add(Trainer_associatition_TrainertoCourse);
		
		allassociationData.put("Trainer", Trainer_association_data);
		
		ObservableList<AssociationInfo> FrontDesk_association_data = FXCollections.observableArrayList();
		
		allassociationData.put("FrontDesk", FrontDesk_association_data);
		
		ObservableList<AssociationInfo> CourseRoom_association_data = FXCollections.observableArrayList();
		
		allassociationData.put("CourseRoom", CourseRoom_association_data);
		
		ObservableList<AssociationInfo> CourseNotification_association_data = FXCollections.observableArrayList();
		
		allassociationData.put("CourseNotification", CourseNotification_association_data);
		
		ObservableList<AssociationInfo> CourseRecord_association_data = FXCollections.observableArrayList();
		
		allassociationData.put("CourseRecord", CourseRecord_association_data);
		
		ObservableList<AssociationInfo> CoursePayment_association_data = FXCollections.observableArrayList();
		AssociationInfo CoursePayment_associatition_CoursePaymenttoMember = new AssociationInfo("CoursePayment", "Member", "CoursePaymenttoMember", true);
		CoursePayment_association_data.add(CoursePayment_associatition_CoursePaymenttoMember);
		
		allassociationData.put("CoursePayment", CoursePayment_association_data);
		
		
		association_statisic.getSelectionModel().selectedItemProperty().addListener(
			    (observable, oldValue, newValue) ->  { 
	
							 		if (newValue != null) {
							 			 try {
							 			 	 if (newValue.getNumber() != 0) {
								 				 //choose object or not
								 				 if (objectindex != -1) {
									 				 Class[] cArg = new Class[1];
									 				 cArg[0] = List.class;
									 				 //reflect updateTableData method
										 			 Method updateob = this.getClass().getMethod("update" + newValue.getTargetClass() + "Table", cArg);
										 			 //find choosen object
										 			 Object selectedob = EntityManager.getAllInstancesOf(newValue.getSourceClass()).get(objectindex);
										 			 //reflect find association method
										 			 Method getAssociatedObject = selectedob.getClass().getMethod("get" + newValue.getAssociationName());
										 			 List r = new LinkedList();
										 			 //one or mulity?
										 			 if (newValue.getIsMultiple() == true) {
											 			 
											 			r = (List) getAssociatedObject.invoke(selectedob);
										 			 }
										 			 else {
										 				r.add(getAssociatedObject.invoke(selectedob));
										 			 }
										 			 //invoke update method
										 			 updateob.invoke(this, r);
										 			  
										 			 
								 				 }
												 //bind updated data to GUI
					 				 			 TableView obs = allObjectTables.get(newValue.getTargetClass());
					 				 			 if (obs != null) {
					 				 				object_statics.setContent(obs);
					 				 				object_statics.setText("Targets Objects " + newValue.getTargetClass() + ":");
					 				 			 }
					 				 		 }
							 			 }
							 			 catch (Exception e) {
							 				 e.printStackTrace();
							 			 }
							 		}
					 		  });
		
	}	
	
	

    //prepare data for contract
	public void prepareData() {
		
		//definition map
		definitions_map = new HashMap<String, String>();
		definitions_map.put("listAllCourseBooked", "user:Member = Member.allInstance()->any(m:Member | m.Id = member_id)\r\r\n");
		definitions_map.put("confirmCancelBook", "user:Member = Member.allInstance()->any(m:Member | m.Id = member_id)\n\rcourse:Course = Course.allInstance()->any(c:Course | c.Id = course_id)\n\rrecord:CourseRecord = CourseRecord.allInstance()->any(re:CourseRecord | re.UserId = member_id and re.CourseId=course_id)\r\r\n");
		definitions_map.put("requestRefund", "user:Member = Member.allInstance()->any(m:Member | m.Id = member_id)\n\rcls:Course = Course.allInstance()->any(c:Course | c.Id = course_id)\n\rpayment:CoursePayment = CoursePayment.allInstance()->any(p:CoursePayment | p.UserId = member_id and p.CourseId = course_id)\r\r\n");
		definitions_map.put("listAllCourseAvailable", "user:Member = Member.allInstance()->any(m:Member | m.Id = member_id)\r\r\n");
		definitions_map.put("chooseOneBooking", "user:Member = Member.allInstance()->any(m:Member | m.Id = member_id)\n\rcls:Course = Course.allInstance()->any(c:Course | c.Id = course_id)\r\r\n");
		definitions_map.put("payFee", "user:Member = Member.allInstance()->any(m:Member | m.Id = member_id)\n\rcls:Course = Course.allInstance()->any(c:Course | c.Id = course_id)\r\r\n");
		definitions_map.put("login_member", "member:Member = Member.allInstance()->any(m:Member | m.Phone = phone)\r\r\n");
		definitions_map.put("login_trainer", "trainer:Trainer = Trainer.allInstance()->any(t:Trainer | t.Phone = phone)\r\r\n");
		definitions_map.put("requestCreateCourse", "trainer:Trainer = Trainer.allInstance()->any(t:Trainer | t.Id = trainer_id)\r\r\n");
		definitions_map.put("confirmCourseInfo", "trainer:Trainer = Trainer.allInstance()->any(t:Trainer | t.Id = trainer_id)\n\rroom:CourseRoom = CourseRoom.allInstance()->any(t:CourseRoom | t.Id = room_id)\r\r\n");
		definitions_map.put("listCourseByTrainer", "trainer:Trainer = Trainer.allInstance()->any(t:Trainer | t.Id = trainer_id)\r\r\n");
		definitions_map.put("chooseSpecificCourse", "cls:Course = Course.allInstance()->any(c:Course | c.Id = course_id)\r\r\n");
		definitions_map.put("confirmUpdateInfo", "cls:Course = Course.allInstance()->any(c:Course | c.Id = course_id)\r\r\n");
		definitions_map.put("listAllCourseByTrainer", "trainer:Trainer = Trainer.allInstance()->any(t:Trainer | t.Id = trainer_id)\r\r\n");
		definitions_map.put("confirmCourseFinish", "cls:Course = Course.allInstance()->any(c:Course | c.Id = course_id)\r\r\n");
		definitions_map.put("requestBonus", "cls:Course = Course.allInstance()->any(c:Course | c.Id = course_id)\n\rtrainer:Trainer = Trainer.allInstance()->any(t:Trainer | t.Id = trainer_id)\r\r\n");
		
		//precondition map
		preconditions_map = new HashMap<String, String>();
		preconditions_map.put("listAllCourseBooked", "user.oclIsUndefined() = false");
		preconditions_map.put("confirmCancelBook", "user.oclIsUndefined() = false and\ncourse.oclIsUndefined() = false and\nrecord.oclIsUndefined() = false\n");
		preconditions_map.put("requestRefund", "user.oclIsUndefined() = false and\ncls.oclIsUndefined() = false and\npayment.oclIsUndefined() = false and\npayment.PayStatus = PayStatus::PAIED\n");
		preconditions_map.put("listAllCourseAvailable", "user.oclIsUndefined() = false");
		preconditions_map.put("chooseOneBooking", "user.oclIsUndefined() = false and\ncls.oclIsUndefined() = false\n");
		preconditions_map.put("payFee", "user.oclIsUndefined() = false and\ncls.oclIsUndefined() = false\n");
		preconditions_map.put("login_member", "member.oclIsUndefined() = false and\nmember.Password = password\n");
		preconditions_map.put("login_trainer", "trainer.oclIsUndefined() = false and\ntrainer.Password = password\n");
		preconditions_map.put("login_frontdesk", "phone = \"admin\" and\npassword = \"admin\"\n");
		preconditions_map.put("input_member_info", "true");
		preconditions_map.put("input_trainer_info", "true");
		preconditions_map.put("requestCreateCourse", "trainer.oclIsUndefined() = false");
		preconditions_map.put("createRoom", "true");
		preconditions_map.put("confirmCourseInfo", "trainer.oclIsUndefined() = false and\nroom.oclIsUndefined() = false\n");
		preconditions_map.put("listCourseByTrainer", "true");
		preconditions_map.put("chooseSpecificCourse", "cls.oclIsUndefined() = false and\ncls.TrainerId = trainer_id\n");
		preconditions_map.put("confirmUpdateInfo", "cls.oclIsUndefined() = false");
		preconditions_map.put("listAllCourseByTrainer", "trainer.oclIsUndefined() = false");
		preconditions_map.put("confirmCourseFinish", "cls.oclIsUndefined() = false and\ncls.TrainerId = trainer_id\n");
		preconditions_map.put("requestBonus", "cls.oclIsUndefined() = false and\ntrainer.oclIsUndefined() = false and\ncls.TrainerId = trainer_id\n");
		
		//postcondition map
		postconditions_map = new HashMap<String, String>();
		postconditions_map.put("listAllCourseBooked", "result = CourseRecord.allInstance()->select(re:CourseRecord | re.UserId = member_id)");
		postconditions_map.put("confirmCancelBook", "record.Status = RecordStatus::CANCEL and\nresult = true\n");
		postconditions_map.put("requestRefund", "payment.PayStatus = PayStatus::REFUND and\nresult = true\n");
		postconditions_map.put("listAllCourseAvailable", "result = Course.allInstance()");
		postconditions_map.put("chooseOneBooking", "let record:CourseRecord inrecord.oclIsNew() and\nrecord.UserId = member_id and\nrecord.CourseId = course_id and\nrecord.status = RecordStatus::NORMAL and\nCourseRecord.allInstance()->includes(record) and\nresult = true\n");
		postconditions_map.put("payFee", "let payment:CoursePayment inpayment.oclIsNew() and\npayment.UserId = member_id and\npayment.CourseId = course_id and\npayment.Price = cls.Cost and\npayment.PayTime = datetime and\npayment.PayStatus = PayStatus::PAIED and\nCoursePayment.allInstance()->includes(payment) and\nresult = true\n");
		postconditions_map.put("login_member", "result = true");
		postconditions_map.put("login_trainer", "result = true");
		postconditions_map.put("login_frontdesk", "result = true");
		postconditions_map.put("input_member_info", "let mem:Member inmem.oclIsNew() and\nmem.Id = id and\nmem.Name = name and\nmem.Phone = phone and\nmem.Description = description and\nmem.Password = \"default123\" and\nmem.RegisterTime = datetime and\nMember.allInstance()->includes(mem) and\nresult = true\n");
		postconditions_map.put("input_trainer_info", "let newTrainer:Trainer innewTrainer.oclIsNew() and\nnewTrainer.Id = id and\nnewTrainer.Name = name and\nnewTrainer.Phone = phone and\nnewTrainer.Description = description and\nnewTrainer.Password = \"default123\" and\nnewTrainer.RegisterTime = datetime and\nTrainer.allInstance()->includes(newTrainer) and\nresult = true\n");
		postconditions_map.put("requestCreateCourse", "result = true");
		postconditions_map.put("createRoom", "let newRoom:CourseRoom innewRoom.oclIsNew() and\nnewRoom.Id = room_id and\nnewRoom.Location = location and\nnewRoom.Capacity = capacity and\nCourseRoom.allInstance()->includes(newRoom) and\nresult = true\n");
		postconditions_map.put("confirmCourseInfo", "let newCourse:Course innewCourse.oclIsNew() and\nnewCourse.RegisterStartTime = register_time and\nnewCourse.RegisterEndTime = register_end_time and\nnewCourse.EventStartTime = begin_time and\nnewCourse.EventEndTime = end_time and\nnewCourse.Id = course_id and\nnewCourse.Name = course_name and\nnewCourse.Cost = cost and\nnewCourse.RoomId = room_id and\nnewCourse.Description = description and\nnewCourse.TrainerId = trainer_id and\nnewCourse.Status = CourseStatus::UNSTARTED and\nCourse.allInstance()->includes(newCourse) and\nresult = true\n");
		postconditions_map.put("listCourseByTrainer", "result = true");
		postconditions_map.put("chooseSpecificCourse", "result = true");
		postconditions_map.put("confirmUpdateInfo", "cls.name = course_name and\ncls.EventStartTime = begin_time and\ncls.EventEndTime = end_time and\ncls.Description = description and\nresult = true\n");
		postconditions_map.put("listAllCourseByTrainer", "result = Course.allInstance()->select(c:Course | c.TrainerId = trainer_id)");
		postconditions_map.put("confirmCourseFinish", "cls.Status = CourseStatus::COMPLETED and\nresult = true\n");
		postconditions_map.put("requestBonus", "result = true");
		
		//service invariants map
		service_invariants_map = new LinkedHashMap<String, String>();
		
		//entity invariants map
		entity_invariants_map = new LinkedHashMap<String, String>();
		
	}
	
	public void generatOperationPane() {
		
		 operationPanels = new LinkedHashMap<String, GridPane>();
		
		 // ==================== GridPane_listAllCourseBooked ====================
		 GridPane listAllCourseBooked = new GridPane();
		 listAllCourseBooked.setHgap(4);
		 listAllCourseBooked.setVgap(6);
		 listAllCourseBooked.setPadding(new Insets(8, 8, 8, 8));
		 
		 ObservableList<Node> listAllCourseBooked_content = listAllCourseBooked.getChildren();
		 Label listAllCourseBooked_member_id_label = new Label("member_id:");
		 listAllCourseBooked_member_id_label.setMinWidth(Region.USE_PREF_SIZE);
		 listAllCourseBooked_content.add(listAllCourseBooked_member_id_label);
		 GridPane.setConstraints(listAllCourseBooked_member_id_label, 0, 0);
		 
		 listAllCourseBooked_member_id_t = new TextField();
		 listAllCourseBooked_content.add(listAllCourseBooked_member_id_t);
		 listAllCourseBooked_member_id_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(listAllCourseBooked_member_id_t, 1, 0);
		 operationPanels.put("listAllCourseBooked", listAllCourseBooked);
		 
		 // ==================== GridPane_confirmCancelBook ====================
		 GridPane confirmCancelBook = new GridPane();
		 confirmCancelBook.setHgap(4);
		 confirmCancelBook.setVgap(6);
		 confirmCancelBook.setPadding(new Insets(8, 8, 8, 8));
		 
		 ObservableList<Node> confirmCancelBook_content = confirmCancelBook.getChildren();
		 Label confirmCancelBook_course_id_label = new Label("course_id:");
		 confirmCancelBook_course_id_label.setMinWidth(Region.USE_PREF_SIZE);
		 confirmCancelBook_content.add(confirmCancelBook_course_id_label);
		 GridPane.setConstraints(confirmCancelBook_course_id_label, 0, 0);
		 
		 confirmCancelBook_course_id_t = new TextField();
		 confirmCancelBook_content.add(confirmCancelBook_course_id_t);
		 confirmCancelBook_course_id_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(confirmCancelBook_course_id_t, 1, 0);
		 Label confirmCancelBook_member_id_label = new Label("member_id:");
		 confirmCancelBook_member_id_label.setMinWidth(Region.USE_PREF_SIZE);
		 confirmCancelBook_content.add(confirmCancelBook_member_id_label);
		 GridPane.setConstraints(confirmCancelBook_member_id_label, 0, 1);
		 
		 confirmCancelBook_member_id_t = new TextField();
		 confirmCancelBook_content.add(confirmCancelBook_member_id_t);
		 confirmCancelBook_member_id_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(confirmCancelBook_member_id_t, 1, 1);
		 operationPanels.put("confirmCancelBook", confirmCancelBook);
		 
		 // ==================== GridPane_requestRefund ====================
		 GridPane requestRefund = new GridPane();
		 requestRefund.setHgap(4);
		 requestRefund.setVgap(6);
		 requestRefund.setPadding(new Insets(8, 8, 8, 8));
		 
		 ObservableList<Node> requestRefund_content = requestRefund.getChildren();
		 Label requestRefund_course_id_label = new Label("course_id:");
		 requestRefund_course_id_label.setMinWidth(Region.USE_PREF_SIZE);
		 requestRefund_content.add(requestRefund_course_id_label);
		 GridPane.setConstraints(requestRefund_course_id_label, 0, 0);
		 
		 requestRefund_course_id_t = new TextField();
		 requestRefund_content.add(requestRefund_course_id_t);
		 requestRefund_course_id_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(requestRefund_course_id_t, 1, 0);
		 Label requestRefund_member_id_label = new Label("member_id:");
		 requestRefund_member_id_label.setMinWidth(Region.USE_PREF_SIZE);
		 requestRefund_content.add(requestRefund_member_id_label);
		 GridPane.setConstraints(requestRefund_member_id_label, 0, 1);
		 
		 requestRefund_member_id_t = new TextField();
		 requestRefund_content.add(requestRefund_member_id_t);
		 requestRefund_member_id_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(requestRefund_member_id_t, 1, 1);
		 operationPanels.put("requestRefund", requestRefund);
		 
		 // ==================== GridPane_listAllCourseAvailable ====================
		 GridPane listAllCourseAvailable = new GridPane();
		 listAllCourseAvailable.setHgap(4);
		 listAllCourseAvailable.setVgap(6);
		 listAllCourseAvailable.setPadding(new Insets(8, 8, 8, 8));
		 
		 ObservableList<Node> listAllCourseAvailable_content = listAllCourseAvailable.getChildren();
		 Label listAllCourseAvailable_member_id_label = new Label("member_id:");
		 listAllCourseAvailable_member_id_label.setMinWidth(Region.USE_PREF_SIZE);
		 listAllCourseAvailable_content.add(listAllCourseAvailable_member_id_label);
		 GridPane.setConstraints(listAllCourseAvailable_member_id_label, 0, 0);
		 
		 listAllCourseAvailable_member_id_t = new TextField();
		 listAllCourseAvailable_content.add(listAllCourseAvailable_member_id_t);
		 listAllCourseAvailable_member_id_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(listAllCourseAvailable_member_id_t, 1, 0);
		 operationPanels.put("listAllCourseAvailable", listAllCourseAvailable);
		 
		 // ==================== GridPane_chooseOneBooking ====================
		 GridPane chooseOneBooking = new GridPane();
		 chooseOneBooking.setHgap(4);
		 chooseOneBooking.setVgap(6);
		 chooseOneBooking.setPadding(new Insets(8, 8, 8, 8));
		 
		 ObservableList<Node> chooseOneBooking_content = chooseOneBooking.getChildren();
		 Label chooseOneBooking_member_id_label = new Label("member_id:");
		 chooseOneBooking_member_id_label.setMinWidth(Region.USE_PREF_SIZE);
		 chooseOneBooking_content.add(chooseOneBooking_member_id_label);
		 GridPane.setConstraints(chooseOneBooking_member_id_label, 0, 0);
		 
		 chooseOneBooking_member_id_t = new TextField();
		 chooseOneBooking_content.add(chooseOneBooking_member_id_t);
		 chooseOneBooking_member_id_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(chooseOneBooking_member_id_t, 1, 0);
		 Label chooseOneBooking_course_id_label = new Label("course_id:");
		 chooseOneBooking_course_id_label.setMinWidth(Region.USE_PREF_SIZE);
		 chooseOneBooking_content.add(chooseOneBooking_course_id_label);
		 GridPane.setConstraints(chooseOneBooking_course_id_label, 0, 1);
		 
		 chooseOneBooking_course_id_t = new TextField();
		 chooseOneBooking_content.add(chooseOneBooking_course_id_t);
		 chooseOneBooking_course_id_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(chooseOneBooking_course_id_t, 1, 1);
		 operationPanels.put("chooseOneBooking", chooseOneBooking);
		 
		 // ==================== GridPane_payFee ====================
		 GridPane payFee = new GridPane();
		 payFee.setHgap(4);
		 payFee.setVgap(6);
		 payFee.setPadding(new Insets(8, 8, 8, 8));
		 
		 ObservableList<Node> payFee_content = payFee.getChildren();
		 Label payFee_member_id_label = new Label("member_id:");
		 payFee_member_id_label.setMinWidth(Region.USE_PREF_SIZE);
		 payFee_content.add(payFee_member_id_label);
		 GridPane.setConstraints(payFee_member_id_label, 0, 0);
		 
		 payFee_member_id_t = new TextField();
		 payFee_content.add(payFee_member_id_t);
		 payFee_member_id_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(payFee_member_id_t, 1, 0);
		 Label payFee_course_id_label = new Label("course_id:");
		 payFee_course_id_label.setMinWidth(Region.USE_PREF_SIZE);
		 payFee_content.add(payFee_course_id_label);
		 GridPane.setConstraints(payFee_course_id_label, 0, 1);
		 
		 payFee_course_id_t = new TextField();
		 payFee_content.add(payFee_course_id_t);
		 payFee_course_id_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(payFee_course_id_t, 1, 1);
		 Label payFee_datetime_label = new Label("datetime:");
		 payFee_datetime_label.setMinWidth(Region.USE_PREF_SIZE);
		 payFee_content.add(payFee_datetime_label);
		 GridPane.setConstraints(payFee_datetime_label, 0, 2);
		 
		 payFee_datetime_t = new TextField();
		 payFee_content.add(payFee_datetime_t);
		 payFee_datetime_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(payFee_datetime_t, 1, 2);
		 operationPanels.put("payFee", payFee);
		 
		 // ==================== GridPane_login_member ====================
		 GridPane login_member = new GridPane();
		 login_member.setHgap(4);
		 login_member.setVgap(6);
		 login_member.setPadding(new Insets(8, 8, 8, 8));
		 
		 ObservableList<Node> login_member_content = login_member.getChildren();
		 Label login_member_phone_label = new Label("phone:");
		 login_member_phone_label.setMinWidth(Region.USE_PREF_SIZE);
		 login_member_content.add(login_member_phone_label);
		 GridPane.setConstraints(login_member_phone_label, 0, 0);
		 
		 login_member_phone_t = new TextField();
		 login_member_content.add(login_member_phone_t);
		 login_member_phone_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(login_member_phone_t, 1, 0);
		 Label login_member_password_label = new Label("password:");
		 login_member_password_label.setMinWidth(Region.USE_PREF_SIZE);
		 login_member_content.add(login_member_password_label);
		 GridPane.setConstraints(login_member_password_label, 0, 1);
		 
		 login_member_password_t = new TextField();
		 login_member_content.add(login_member_password_t);
		 login_member_password_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(login_member_password_t, 1, 1);
		 operationPanels.put("login_member", login_member);
		 
		 // ==================== GridPane_login_trainer ====================
		 GridPane login_trainer = new GridPane();
		 login_trainer.setHgap(4);
		 login_trainer.setVgap(6);
		 login_trainer.setPadding(new Insets(8, 8, 8, 8));
		 
		 ObservableList<Node> login_trainer_content = login_trainer.getChildren();
		 Label login_trainer_phone_label = new Label("phone:");
		 login_trainer_phone_label.setMinWidth(Region.USE_PREF_SIZE);
		 login_trainer_content.add(login_trainer_phone_label);
		 GridPane.setConstraints(login_trainer_phone_label, 0, 0);
		 
		 login_trainer_phone_t = new TextField();
		 login_trainer_content.add(login_trainer_phone_t);
		 login_trainer_phone_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(login_trainer_phone_t, 1, 0);
		 Label login_trainer_password_label = new Label("password:");
		 login_trainer_password_label.setMinWidth(Region.USE_PREF_SIZE);
		 login_trainer_content.add(login_trainer_password_label);
		 GridPane.setConstraints(login_trainer_password_label, 0, 1);
		 
		 login_trainer_password_t = new TextField();
		 login_trainer_content.add(login_trainer_password_t);
		 login_trainer_password_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(login_trainer_password_t, 1, 1);
		 operationPanels.put("login_trainer", login_trainer);
		 
		 // ==================== GridPane_login_frontdesk ====================
		 GridPane login_frontdesk = new GridPane();
		 login_frontdesk.setHgap(4);
		 login_frontdesk.setVgap(6);
		 login_frontdesk.setPadding(new Insets(8, 8, 8, 8));
		 
		 ObservableList<Node> login_frontdesk_content = login_frontdesk.getChildren();
		 Label login_frontdesk_phone_label = new Label("phone:");
		 login_frontdesk_phone_label.setMinWidth(Region.USE_PREF_SIZE);
		 login_frontdesk_content.add(login_frontdesk_phone_label);
		 GridPane.setConstraints(login_frontdesk_phone_label, 0, 0);
		 
		 login_frontdesk_phone_t = new TextField();
		 login_frontdesk_content.add(login_frontdesk_phone_t);
		 login_frontdesk_phone_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(login_frontdesk_phone_t, 1, 0);
		 Label login_frontdesk_password_label = new Label("password:");
		 login_frontdesk_password_label.setMinWidth(Region.USE_PREF_SIZE);
		 login_frontdesk_content.add(login_frontdesk_password_label);
		 GridPane.setConstraints(login_frontdesk_password_label, 0, 1);
		 
		 login_frontdesk_password_t = new TextField();
		 login_frontdesk_content.add(login_frontdesk_password_t);
		 login_frontdesk_password_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(login_frontdesk_password_t, 1, 1);
		 operationPanels.put("login_frontdesk", login_frontdesk);
		 
		 // ==================== GridPane_input_member_info ====================
		 GridPane input_member_info = new GridPane();
		 input_member_info.setHgap(4);
		 input_member_info.setVgap(6);
		 input_member_info.setPadding(new Insets(8, 8, 8, 8));
		 
		 ObservableList<Node> input_member_info_content = input_member_info.getChildren();
		 Label input_member_info_id_label = new Label("id:");
		 input_member_info_id_label.setMinWidth(Region.USE_PREF_SIZE);
		 input_member_info_content.add(input_member_info_id_label);
		 GridPane.setConstraints(input_member_info_id_label, 0, 0);
		 
		 input_member_info_id_t = new TextField();
		 input_member_info_content.add(input_member_info_id_t);
		 input_member_info_id_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(input_member_info_id_t, 1, 0);
		 Label input_member_info_name_label = new Label("name:");
		 input_member_info_name_label.setMinWidth(Region.USE_PREF_SIZE);
		 input_member_info_content.add(input_member_info_name_label);
		 GridPane.setConstraints(input_member_info_name_label, 0, 1);
		 
		 input_member_info_name_t = new TextField();
		 input_member_info_content.add(input_member_info_name_t);
		 input_member_info_name_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(input_member_info_name_t, 1, 1);
		 Label input_member_info_age_label = new Label("age:");
		 input_member_info_age_label.setMinWidth(Region.USE_PREF_SIZE);
		 input_member_info_content.add(input_member_info_age_label);
		 GridPane.setConstraints(input_member_info_age_label, 0, 2);
		 
		 input_member_info_age_t = new TextField();
		 input_member_info_content.add(input_member_info_age_t);
		 input_member_info_age_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(input_member_info_age_t, 1, 2);
		 Label input_member_info_phone_label = new Label("phone:");
		 input_member_info_phone_label.setMinWidth(Region.USE_PREF_SIZE);
		 input_member_info_content.add(input_member_info_phone_label);
		 GridPane.setConstraints(input_member_info_phone_label, 0, 3);
		 
		 input_member_info_phone_t = new TextField();
		 input_member_info_content.add(input_member_info_phone_t);
		 input_member_info_phone_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(input_member_info_phone_t, 1, 3);
		 Label input_member_info_description_label = new Label("description:");
		 input_member_info_description_label.setMinWidth(Region.USE_PREF_SIZE);
		 input_member_info_content.add(input_member_info_description_label);
		 GridPane.setConstraints(input_member_info_description_label, 0, 4);
		 
		 input_member_info_description_t = new TextField();
		 input_member_info_content.add(input_member_info_description_t);
		 input_member_info_description_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(input_member_info_description_t, 1, 4);
		 Label input_member_info_datetime_label = new Label("datetime:");
		 input_member_info_datetime_label.setMinWidth(Region.USE_PREF_SIZE);
		 input_member_info_content.add(input_member_info_datetime_label);
		 GridPane.setConstraints(input_member_info_datetime_label, 0, 5);
		 
		 input_member_info_datetime_t = new TextField();
		 input_member_info_content.add(input_member_info_datetime_t);
		 input_member_info_datetime_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(input_member_info_datetime_t, 1, 5);
		 operationPanels.put("input_member_info", input_member_info);
		 
		 // ==================== GridPane_input_trainer_info ====================
		 GridPane input_trainer_info = new GridPane();
		 input_trainer_info.setHgap(4);
		 input_trainer_info.setVgap(6);
		 input_trainer_info.setPadding(new Insets(8, 8, 8, 8));
		 
		 ObservableList<Node> input_trainer_info_content = input_trainer_info.getChildren();
		 Label input_trainer_info_id_label = new Label("id:");
		 input_trainer_info_id_label.setMinWidth(Region.USE_PREF_SIZE);
		 input_trainer_info_content.add(input_trainer_info_id_label);
		 GridPane.setConstraints(input_trainer_info_id_label, 0, 0);
		 
		 input_trainer_info_id_t = new TextField();
		 input_trainer_info_content.add(input_trainer_info_id_t);
		 input_trainer_info_id_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(input_trainer_info_id_t, 1, 0);
		 Label input_trainer_info_name_label = new Label("name:");
		 input_trainer_info_name_label.setMinWidth(Region.USE_PREF_SIZE);
		 input_trainer_info_content.add(input_trainer_info_name_label);
		 GridPane.setConstraints(input_trainer_info_name_label, 0, 1);
		 
		 input_trainer_info_name_t = new TextField();
		 input_trainer_info_content.add(input_trainer_info_name_t);
		 input_trainer_info_name_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(input_trainer_info_name_t, 1, 1);
		 Label input_trainer_info_age_label = new Label("age:");
		 input_trainer_info_age_label.setMinWidth(Region.USE_PREF_SIZE);
		 input_trainer_info_content.add(input_trainer_info_age_label);
		 GridPane.setConstraints(input_trainer_info_age_label, 0, 2);
		 
		 input_trainer_info_age_t = new TextField();
		 input_trainer_info_content.add(input_trainer_info_age_t);
		 input_trainer_info_age_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(input_trainer_info_age_t, 1, 2);
		 Label input_trainer_info_phone_label = new Label("phone:");
		 input_trainer_info_phone_label.setMinWidth(Region.USE_PREF_SIZE);
		 input_trainer_info_content.add(input_trainer_info_phone_label);
		 GridPane.setConstraints(input_trainer_info_phone_label, 0, 3);
		 
		 input_trainer_info_phone_t = new TextField();
		 input_trainer_info_content.add(input_trainer_info_phone_t);
		 input_trainer_info_phone_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(input_trainer_info_phone_t, 1, 3);
		 Label input_trainer_info_description_label = new Label("description:");
		 input_trainer_info_description_label.setMinWidth(Region.USE_PREF_SIZE);
		 input_trainer_info_content.add(input_trainer_info_description_label);
		 GridPane.setConstraints(input_trainer_info_description_label, 0, 4);
		 
		 input_trainer_info_description_t = new TextField();
		 input_trainer_info_content.add(input_trainer_info_description_t);
		 input_trainer_info_description_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(input_trainer_info_description_t, 1, 4);
		 Label input_trainer_info_datetime_label = new Label("datetime:");
		 input_trainer_info_datetime_label.setMinWidth(Region.USE_PREF_SIZE);
		 input_trainer_info_content.add(input_trainer_info_datetime_label);
		 GridPane.setConstraints(input_trainer_info_datetime_label, 0, 5);
		 
		 input_trainer_info_datetime_t = new TextField();
		 input_trainer_info_content.add(input_trainer_info_datetime_t);
		 input_trainer_info_datetime_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(input_trainer_info_datetime_t, 1, 5);
		 operationPanels.put("input_trainer_info", input_trainer_info);
		 
		 // ==================== GridPane_requestCreateCourse ====================
		 GridPane requestCreateCourse = new GridPane();
		 requestCreateCourse.setHgap(4);
		 requestCreateCourse.setVgap(6);
		 requestCreateCourse.setPadding(new Insets(8, 8, 8, 8));
		 
		 ObservableList<Node> requestCreateCourse_content = requestCreateCourse.getChildren();
		 Label requestCreateCourse_trainer_id_label = new Label("trainer_id:");
		 requestCreateCourse_trainer_id_label.setMinWidth(Region.USE_PREF_SIZE);
		 requestCreateCourse_content.add(requestCreateCourse_trainer_id_label);
		 GridPane.setConstraints(requestCreateCourse_trainer_id_label, 0, 0);
		 
		 requestCreateCourse_trainer_id_t = new TextField();
		 requestCreateCourse_content.add(requestCreateCourse_trainer_id_t);
		 requestCreateCourse_trainer_id_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(requestCreateCourse_trainer_id_t, 1, 0);
		 operationPanels.put("requestCreateCourse", requestCreateCourse);
		 
		 // ==================== GridPane_createRoom ====================
		 GridPane createRoom = new GridPane();
		 createRoom.setHgap(4);
		 createRoom.setVgap(6);
		 createRoom.setPadding(new Insets(8, 8, 8, 8));
		 
		 ObservableList<Node> createRoom_content = createRoom.getChildren();
		 Label createRoom_room_id_label = new Label("room_id:");
		 createRoom_room_id_label.setMinWidth(Region.USE_PREF_SIZE);
		 createRoom_content.add(createRoom_room_id_label);
		 GridPane.setConstraints(createRoom_room_id_label, 0, 0);
		 
		 createRoom_room_id_t = new TextField();
		 createRoom_content.add(createRoom_room_id_t);
		 createRoom_room_id_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(createRoom_room_id_t, 1, 0);
		 Label createRoom_location_label = new Label("location:");
		 createRoom_location_label.setMinWidth(Region.USE_PREF_SIZE);
		 createRoom_content.add(createRoom_location_label);
		 GridPane.setConstraints(createRoom_location_label, 0, 1);
		 
		 createRoom_location_t = new TextField();
		 createRoom_content.add(createRoom_location_t);
		 createRoom_location_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(createRoom_location_t, 1, 1);
		 Label createRoom_capacity_label = new Label("capacity:");
		 createRoom_capacity_label.setMinWidth(Region.USE_PREF_SIZE);
		 createRoom_content.add(createRoom_capacity_label);
		 GridPane.setConstraints(createRoom_capacity_label, 0, 2);
		 
		 createRoom_capacity_t = new TextField();
		 createRoom_content.add(createRoom_capacity_t);
		 createRoom_capacity_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(createRoom_capacity_t, 1, 2);
		 operationPanels.put("createRoom", createRoom);
		 
		 // ==================== GridPane_confirmCourseInfo ====================
		 GridPane confirmCourseInfo = new GridPane();
		 confirmCourseInfo.setHgap(4);
		 confirmCourseInfo.setVgap(6);
		 confirmCourseInfo.setPadding(new Insets(8, 8, 8, 8));
		 
		 ObservableList<Node> confirmCourseInfo_content = confirmCourseInfo.getChildren();
		 Label confirmCourseInfo_room_id_label = new Label("room_id:");
		 confirmCourseInfo_room_id_label.setMinWidth(Region.USE_PREF_SIZE);
		 confirmCourseInfo_content.add(confirmCourseInfo_room_id_label);
		 GridPane.setConstraints(confirmCourseInfo_room_id_label, 0, 0);
		 
		 confirmCourseInfo_room_id_t = new TextField();
		 confirmCourseInfo_content.add(confirmCourseInfo_room_id_t);
		 confirmCourseInfo_room_id_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(confirmCourseInfo_room_id_t, 1, 0);
		 Label confirmCourseInfo_course_id_label = new Label("course_id:");
		 confirmCourseInfo_course_id_label.setMinWidth(Region.USE_PREF_SIZE);
		 confirmCourseInfo_content.add(confirmCourseInfo_course_id_label);
		 GridPane.setConstraints(confirmCourseInfo_course_id_label, 0, 1);
		 
		 confirmCourseInfo_course_id_t = new TextField();
		 confirmCourseInfo_content.add(confirmCourseInfo_course_id_t);
		 confirmCourseInfo_course_id_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(confirmCourseInfo_course_id_t, 1, 1);
		 Label confirmCourseInfo_register_time_label = new Label("register_time:");
		 confirmCourseInfo_register_time_label.setMinWidth(Region.USE_PREF_SIZE);
		 confirmCourseInfo_content.add(confirmCourseInfo_register_time_label);
		 GridPane.setConstraints(confirmCourseInfo_register_time_label, 0, 2);
		 
		 confirmCourseInfo_register_time_t = new TextField();
		 confirmCourseInfo_content.add(confirmCourseInfo_register_time_t);
		 confirmCourseInfo_register_time_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(confirmCourseInfo_register_time_t, 1, 2);
		 Label confirmCourseInfo_register_end_time_label = new Label("register_end_time:");
		 confirmCourseInfo_register_end_time_label.setMinWidth(Region.USE_PREF_SIZE);
		 confirmCourseInfo_content.add(confirmCourseInfo_register_end_time_label);
		 GridPane.setConstraints(confirmCourseInfo_register_end_time_label, 0, 3);
		 
		 confirmCourseInfo_register_end_time_t = new TextField();
		 confirmCourseInfo_content.add(confirmCourseInfo_register_end_time_t);
		 confirmCourseInfo_register_end_time_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(confirmCourseInfo_register_end_time_t, 1, 3);
		 Label confirmCourseInfo_begin_time_label = new Label("begin_time:");
		 confirmCourseInfo_begin_time_label.setMinWidth(Region.USE_PREF_SIZE);
		 confirmCourseInfo_content.add(confirmCourseInfo_begin_time_label);
		 GridPane.setConstraints(confirmCourseInfo_begin_time_label, 0, 4);
		 
		 confirmCourseInfo_begin_time_t = new TextField();
		 confirmCourseInfo_content.add(confirmCourseInfo_begin_time_t);
		 confirmCourseInfo_begin_time_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(confirmCourseInfo_begin_time_t, 1, 4);
		 Label confirmCourseInfo_end_time_label = new Label("end_time:");
		 confirmCourseInfo_end_time_label.setMinWidth(Region.USE_PREF_SIZE);
		 confirmCourseInfo_content.add(confirmCourseInfo_end_time_label);
		 GridPane.setConstraints(confirmCourseInfo_end_time_label, 0, 5);
		 
		 confirmCourseInfo_end_time_t = new TextField();
		 confirmCourseInfo_content.add(confirmCourseInfo_end_time_t);
		 confirmCourseInfo_end_time_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(confirmCourseInfo_end_time_t, 1, 5);
		 Label confirmCourseInfo_course_name_label = new Label("course_name:");
		 confirmCourseInfo_course_name_label.setMinWidth(Region.USE_PREF_SIZE);
		 confirmCourseInfo_content.add(confirmCourseInfo_course_name_label);
		 GridPane.setConstraints(confirmCourseInfo_course_name_label, 0, 6);
		 
		 confirmCourseInfo_course_name_t = new TextField();
		 confirmCourseInfo_content.add(confirmCourseInfo_course_name_t);
		 confirmCourseInfo_course_name_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(confirmCourseInfo_course_name_t, 1, 6);
		 Label confirmCourseInfo_description_label = new Label("description:");
		 confirmCourseInfo_description_label.setMinWidth(Region.USE_PREF_SIZE);
		 confirmCourseInfo_content.add(confirmCourseInfo_description_label);
		 GridPane.setConstraints(confirmCourseInfo_description_label, 0, 7);
		 
		 confirmCourseInfo_description_t = new TextField();
		 confirmCourseInfo_content.add(confirmCourseInfo_description_t);
		 confirmCourseInfo_description_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(confirmCourseInfo_description_t, 1, 7);
		 Label confirmCourseInfo_trainer_id_label = new Label("trainer_id:");
		 confirmCourseInfo_trainer_id_label.setMinWidth(Region.USE_PREF_SIZE);
		 confirmCourseInfo_content.add(confirmCourseInfo_trainer_id_label);
		 GridPane.setConstraints(confirmCourseInfo_trainer_id_label, 0, 8);
		 
		 confirmCourseInfo_trainer_id_t = new TextField();
		 confirmCourseInfo_content.add(confirmCourseInfo_trainer_id_t);
		 confirmCourseInfo_trainer_id_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(confirmCourseInfo_trainer_id_t, 1, 8);
		 Label confirmCourseInfo_cost_label = new Label("cost:");
		 confirmCourseInfo_cost_label.setMinWidth(Region.USE_PREF_SIZE);
		 confirmCourseInfo_content.add(confirmCourseInfo_cost_label);
		 GridPane.setConstraints(confirmCourseInfo_cost_label, 0, 9);
		 
		 confirmCourseInfo_cost_t = new TextField();
		 confirmCourseInfo_content.add(confirmCourseInfo_cost_t);
		 confirmCourseInfo_cost_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(confirmCourseInfo_cost_t, 1, 9);
		 operationPanels.put("confirmCourseInfo", confirmCourseInfo);
		 
		 // ==================== GridPane_listCourseByTrainer ====================
		 GridPane listCourseByTrainer = new GridPane();
		 listCourseByTrainer.setHgap(4);
		 listCourseByTrainer.setVgap(6);
		 listCourseByTrainer.setPadding(new Insets(8, 8, 8, 8));
		 
		 ObservableList<Node> listCourseByTrainer_content = listCourseByTrainer.getChildren();
		 Label listCourseByTrainer_trainer_id_label = new Label("trainer_id:");
		 listCourseByTrainer_trainer_id_label.setMinWidth(Region.USE_PREF_SIZE);
		 listCourseByTrainer_content.add(listCourseByTrainer_trainer_id_label);
		 GridPane.setConstraints(listCourseByTrainer_trainer_id_label, 0, 0);
		 
		 listCourseByTrainer_trainer_id_t = new TextField();
		 listCourseByTrainer_content.add(listCourseByTrainer_trainer_id_t);
		 listCourseByTrainer_trainer_id_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(listCourseByTrainer_trainer_id_t, 1, 0);
		 operationPanels.put("listCourseByTrainer", listCourseByTrainer);
		 
		 // ==================== GridPane_chooseSpecificCourse ====================
		 GridPane chooseSpecificCourse = new GridPane();
		 chooseSpecificCourse.setHgap(4);
		 chooseSpecificCourse.setVgap(6);
		 chooseSpecificCourse.setPadding(new Insets(8, 8, 8, 8));
		 
		 ObservableList<Node> chooseSpecificCourse_content = chooseSpecificCourse.getChildren();
		 Label chooseSpecificCourse_trainer_id_label = new Label("trainer_id:");
		 chooseSpecificCourse_trainer_id_label.setMinWidth(Region.USE_PREF_SIZE);
		 chooseSpecificCourse_content.add(chooseSpecificCourse_trainer_id_label);
		 GridPane.setConstraints(chooseSpecificCourse_trainer_id_label, 0, 0);
		 
		 chooseSpecificCourse_trainer_id_t = new TextField();
		 chooseSpecificCourse_content.add(chooseSpecificCourse_trainer_id_t);
		 chooseSpecificCourse_trainer_id_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(chooseSpecificCourse_trainer_id_t, 1, 0);
		 Label chooseSpecificCourse_course_id_label = new Label("course_id:");
		 chooseSpecificCourse_course_id_label.setMinWidth(Region.USE_PREF_SIZE);
		 chooseSpecificCourse_content.add(chooseSpecificCourse_course_id_label);
		 GridPane.setConstraints(chooseSpecificCourse_course_id_label, 0, 1);
		 
		 chooseSpecificCourse_course_id_t = new TextField();
		 chooseSpecificCourse_content.add(chooseSpecificCourse_course_id_t);
		 chooseSpecificCourse_course_id_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(chooseSpecificCourse_course_id_t, 1, 1);
		 operationPanels.put("chooseSpecificCourse", chooseSpecificCourse);
		 
		 // ==================== GridPane_confirmUpdateInfo ====================
		 GridPane confirmUpdateInfo = new GridPane();
		 confirmUpdateInfo.setHgap(4);
		 confirmUpdateInfo.setVgap(6);
		 confirmUpdateInfo.setPadding(new Insets(8, 8, 8, 8));
		 
		 ObservableList<Node> confirmUpdateInfo_content = confirmUpdateInfo.getChildren();
		 Label confirmUpdateInfo_course_id_label = new Label("course_id:");
		 confirmUpdateInfo_course_id_label.setMinWidth(Region.USE_PREF_SIZE);
		 confirmUpdateInfo_content.add(confirmUpdateInfo_course_id_label);
		 GridPane.setConstraints(confirmUpdateInfo_course_id_label, 0, 0);
		 
		 confirmUpdateInfo_course_id_t = new TextField();
		 confirmUpdateInfo_content.add(confirmUpdateInfo_course_id_t);
		 confirmUpdateInfo_course_id_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(confirmUpdateInfo_course_id_t, 1, 0);
		 Label confirmUpdateInfo_course_name_label = new Label("course_name:");
		 confirmUpdateInfo_course_name_label.setMinWidth(Region.USE_PREF_SIZE);
		 confirmUpdateInfo_content.add(confirmUpdateInfo_course_name_label);
		 GridPane.setConstraints(confirmUpdateInfo_course_name_label, 0, 1);
		 
		 confirmUpdateInfo_course_name_t = new TextField();
		 confirmUpdateInfo_content.add(confirmUpdateInfo_course_name_t);
		 confirmUpdateInfo_course_name_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(confirmUpdateInfo_course_name_t, 1, 1);
		 Label confirmUpdateInfo_begin_time_label = new Label("begin_time:");
		 confirmUpdateInfo_begin_time_label.setMinWidth(Region.USE_PREF_SIZE);
		 confirmUpdateInfo_content.add(confirmUpdateInfo_begin_time_label);
		 GridPane.setConstraints(confirmUpdateInfo_begin_time_label, 0, 2);
		 
		 confirmUpdateInfo_begin_time_t = new TextField();
		 confirmUpdateInfo_content.add(confirmUpdateInfo_begin_time_t);
		 confirmUpdateInfo_begin_time_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(confirmUpdateInfo_begin_time_t, 1, 2);
		 Label confirmUpdateInfo_end_time_label = new Label("end_time:");
		 confirmUpdateInfo_end_time_label.setMinWidth(Region.USE_PREF_SIZE);
		 confirmUpdateInfo_content.add(confirmUpdateInfo_end_time_label);
		 GridPane.setConstraints(confirmUpdateInfo_end_time_label, 0, 3);
		 
		 confirmUpdateInfo_end_time_t = new TextField();
		 confirmUpdateInfo_content.add(confirmUpdateInfo_end_time_t);
		 confirmUpdateInfo_end_time_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(confirmUpdateInfo_end_time_t, 1, 3);
		 Label confirmUpdateInfo_description_label = new Label("description:");
		 confirmUpdateInfo_description_label.setMinWidth(Region.USE_PREF_SIZE);
		 confirmUpdateInfo_content.add(confirmUpdateInfo_description_label);
		 GridPane.setConstraints(confirmUpdateInfo_description_label, 0, 4);
		 
		 confirmUpdateInfo_description_t = new TextField();
		 confirmUpdateInfo_content.add(confirmUpdateInfo_description_t);
		 confirmUpdateInfo_description_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(confirmUpdateInfo_description_t, 1, 4);
		 Label confirmUpdateInfo_trainer_id_label = new Label("trainer_id:");
		 confirmUpdateInfo_trainer_id_label.setMinWidth(Region.USE_PREF_SIZE);
		 confirmUpdateInfo_content.add(confirmUpdateInfo_trainer_id_label);
		 GridPane.setConstraints(confirmUpdateInfo_trainer_id_label, 0, 5);
		 
		 confirmUpdateInfo_trainer_id_t = new TextField();
		 confirmUpdateInfo_content.add(confirmUpdateInfo_trainer_id_t);
		 confirmUpdateInfo_trainer_id_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(confirmUpdateInfo_trainer_id_t, 1, 5);
		 operationPanels.put("confirmUpdateInfo", confirmUpdateInfo);
		 
		 // ==================== GridPane_listAllCourseByTrainer ====================
		 GridPane listAllCourseByTrainer = new GridPane();
		 listAllCourseByTrainer.setHgap(4);
		 listAllCourseByTrainer.setVgap(6);
		 listAllCourseByTrainer.setPadding(new Insets(8, 8, 8, 8));
		 
		 ObservableList<Node> listAllCourseByTrainer_content = listAllCourseByTrainer.getChildren();
		 Label listAllCourseByTrainer_trainer_id_label = new Label("trainer_id:");
		 listAllCourseByTrainer_trainer_id_label.setMinWidth(Region.USE_PREF_SIZE);
		 listAllCourseByTrainer_content.add(listAllCourseByTrainer_trainer_id_label);
		 GridPane.setConstraints(listAllCourseByTrainer_trainer_id_label, 0, 0);
		 
		 listAllCourseByTrainer_trainer_id_t = new TextField();
		 listAllCourseByTrainer_content.add(listAllCourseByTrainer_trainer_id_t);
		 listAllCourseByTrainer_trainer_id_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(listAllCourseByTrainer_trainer_id_t, 1, 0);
		 operationPanels.put("listAllCourseByTrainer", listAllCourseByTrainer);
		 
		 // ==================== GridPane_confirmCourseFinish ====================
		 GridPane confirmCourseFinish = new GridPane();
		 confirmCourseFinish.setHgap(4);
		 confirmCourseFinish.setVgap(6);
		 confirmCourseFinish.setPadding(new Insets(8, 8, 8, 8));
		 
		 ObservableList<Node> confirmCourseFinish_content = confirmCourseFinish.getChildren();
		 Label confirmCourseFinish_trainer_id_label = new Label("trainer_id:");
		 confirmCourseFinish_trainer_id_label.setMinWidth(Region.USE_PREF_SIZE);
		 confirmCourseFinish_content.add(confirmCourseFinish_trainer_id_label);
		 GridPane.setConstraints(confirmCourseFinish_trainer_id_label, 0, 0);
		 
		 confirmCourseFinish_trainer_id_t = new TextField();
		 confirmCourseFinish_content.add(confirmCourseFinish_trainer_id_t);
		 confirmCourseFinish_trainer_id_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(confirmCourseFinish_trainer_id_t, 1, 0);
		 Label confirmCourseFinish_course_id_label = new Label("course_id:");
		 confirmCourseFinish_course_id_label.setMinWidth(Region.USE_PREF_SIZE);
		 confirmCourseFinish_content.add(confirmCourseFinish_course_id_label);
		 GridPane.setConstraints(confirmCourseFinish_course_id_label, 0, 1);
		 
		 confirmCourseFinish_course_id_t = new TextField();
		 confirmCourseFinish_content.add(confirmCourseFinish_course_id_t);
		 confirmCourseFinish_course_id_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(confirmCourseFinish_course_id_t, 1, 1);
		 operationPanels.put("confirmCourseFinish", confirmCourseFinish);
		 
		 // ==================== GridPane_requestBonus ====================
		 GridPane requestBonus = new GridPane();
		 requestBonus.setHgap(4);
		 requestBonus.setVgap(6);
		 requestBonus.setPadding(new Insets(8, 8, 8, 8));
		 
		 ObservableList<Node> requestBonus_content = requestBonus.getChildren();
		 Label requestBonus_trainer_id_label = new Label("trainer_id:");
		 requestBonus_trainer_id_label.setMinWidth(Region.USE_PREF_SIZE);
		 requestBonus_content.add(requestBonus_trainer_id_label);
		 GridPane.setConstraints(requestBonus_trainer_id_label, 0, 0);
		 
		 requestBonus_trainer_id_t = new TextField();
		 requestBonus_content.add(requestBonus_trainer_id_t);
		 requestBonus_trainer_id_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(requestBonus_trainer_id_t, 1, 0);
		 Label requestBonus_course_id_label = new Label("course_id:");
		 requestBonus_course_id_label.setMinWidth(Region.USE_PREF_SIZE);
		 requestBonus_content.add(requestBonus_course_id_label);
		 GridPane.setConstraints(requestBonus_course_id_label, 0, 1);
		 
		 requestBonus_course_id_t = new TextField();
		 requestBonus_content.add(requestBonus_course_id_t);
		 requestBonus_course_id_t.setMinWidth(Region.USE_PREF_SIZE);
		 GridPane.setConstraints(requestBonus_course_id_t, 1, 1);
		 operationPanels.put("requestBonus", requestBonus);
		 
	}	

	public void actorTreeViewBinding() {
		
		 

		TreeItem<String> treeRootadministrator = new TreeItem<String>("Root node");
		
		
					 			
						 		
		treeRootadministrator.getChildren().addAll(Arrays.asList(
				));	
				
	 			treeRootadministrator.setExpanded(true);

		actor_treeview_administrator.setShowRoot(false);
		actor_treeview_administrator.setRoot(treeRootadministrator);
	 		
		actor_treeview_administrator.getSelectionModel().selectedItemProperty().addListener(
		 				 (observable, oldValue, newValue) -> { 
		 				 								
		 				 							 //clear the previous return
		 											 operation_return_pane.setContent(new Label());
		 											 
		 				 							 clickedOp = newValue.getValue();
		 				 							 GridPane op = operationPanels.get(clickedOp);
		 				 							 VBox vb = opInvariantPanel.get(clickedOp);
		 				 							 
		 				 							 //op pannel
		 				 							 if (op != null) {
		 				 								 operation_paras.setContent(operationPanels.get(newValue.getValue()));
		 				 								 
		 				 								 ObservableList<Node> l = operationPanels.get(newValue.getValue()).getChildren();
		 				 								 choosenOperation = new LinkedList<TextField>();
		 				 								 for (Node n : l) {
		 				 								 	 if (n instanceof TextField) {
		 				 								 	 	choosenOperation.add((TextField)n);
		 				 								 	  }
		 				 								 }
		 				 								 
		 				 								 definition.setText(definitions_map.get(newValue.getValue()));
		 				 								 precondition.setText(preconditions_map.get(newValue.getValue()));
		 				 								 postcondition.setText(postconditions_map.get(newValue.getValue()));
		 				 								 
		 				 						     }
		 				 							 else {
		 				 								 Label l = new Label(newValue.getValue() + " is no contract information in requirement model.");
		 				 								 l.setPadding(new Insets(8, 8, 8, 8));
		 				 								 operation_paras.setContent(l);
		 				 							 }	
		 				 							 
		 				 							 //op invariants
		 				 							 if (vb != null) {
		 				 							 	ScrollPane scrollPane = new ScrollPane(vb);
		 				 							 	scrollPane.setFitToWidth(true);
		 				 							 	invariants_panes.setMaxHeight(200); 
		 				 							 	//all_invariant_pane.setContent(scrollPane);	
		 				 							 	
		 				 							 	invariants_panes.setContent(scrollPane);
		 				 							 } else {
		 				 							 	 Label l = new Label(newValue.getValue() + " is no related invariants");
		 				 							     l.setPadding(new Insets(8, 8, 8, 8));
		 				 							     invariants_panes.setContent(l);
		 				 							 }
		 				 							 
		 				 							 //reset pre- and post-conditions area color
		 				 							 precondition.setStyle("-fx-background-color:#FFFFFF; -fx-control-inner-background: #FFFFFF ");
		 				 							 postcondition.setStyle("-fx-background-color:#FFFFFF; -fx-control-inner-background: #FFFFFF");
		 				 							 //reset condition panel title
		 				 							 precondition_pane.setText("Precondition");
		 				 							 postcondition_pane.setText("Postcondition");
		 				 						} 
		 				 				);

		
		
		 
		TreeItem<String> treeRootmember = new TreeItem<String>("Root node");
			TreeItem<String> subTreeRoot_book_Course = new TreeItem<String>("book_Course");
			subTreeRoot_book_Course.getChildren().addAll(Arrays.asList(			 		    
					 	new TreeItem<String>("listAllCourseAvailable"),
					 	new TreeItem<String>("chooseOneBooking"),
					 	new TreeItem<String>("payFee")
				 		));	
			TreeItem<String> subTreeRoot_cancel_book_Course = new TreeItem<String>("cancel_book_Course");
			subTreeRoot_cancel_book_Course.getChildren().addAll(Arrays.asList(			 		    
					 	new TreeItem<String>("listAllCourseBooked"),
					 	new TreeItem<String>("confirmCancelBook"),
					 	new TreeItem<String>("requestRefund")
				 		));	
		
		treeRootmember.getChildren().addAll(Arrays.asList(
			subTreeRoot_book_Course,
			subTreeRoot_cancel_book_Course
					));
		
		treeRootmember.setExpanded(true);

		actor_treeview_member.setShowRoot(false);
		actor_treeview_member.setRoot(treeRootmember);
		
		//TreeView click, then open the GridPane for inputing parameters
		actor_treeview_member.getSelectionModel().selectedItemProperty().addListener(
						 (observable, oldValue, newValue) -> { 
						 								
						 							 //clear the previous return
													 operation_return_pane.setContent(new Label());
													 
						 							 clickedOp = newValue.getValue();
						 							 GridPane op = operationPanels.get(clickedOp);
						 							 VBox vb = opInvariantPanel.get(clickedOp);
						 							 
						 							 //op pannel
						 							 if (op != null) {
						 								 operation_paras.setContent(operationPanels.get(newValue.getValue()));
						 								 
						 								 ObservableList<Node> l = operationPanels.get(newValue.getValue()).getChildren();
						 								 choosenOperation = new LinkedList<TextField>();
						 								 for (Node n : l) {
						 								 	 if (n instanceof TextField) {
						 								 	 	choosenOperation.add((TextField)n);
						 								 	  }
						 								 }
						 								 
						 								 definition.setText(definitions_map.get(newValue.getValue()));
						 								 precondition.setText(preconditions_map.get(newValue.getValue()));
						 								 postcondition.setText(postconditions_map.get(newValue.getValue()));
						 								 
						 						     }
						 							 else {
						 								 Label l = new Label(newValue.getValue() + " is no contract information in requirement model.");
						 								 l.setPadding(new Insets(8, 8, 8, 8));
						 								 operation_paras.setContent(l);
						 							 }	
						 							 
						 							 //op invariants
						 							 if (vb != null) {
						 							 	ScrollPane scrollPane = new ScrollPane(vb);
						 							 	scrollPane.setFitToWidth(true);
						 							 	invariants_panes.setMaxHeight(200); 
						 							 	//all_invariant_pane.setContent(scrollPane);	
						 							 	
						 							 	invariants_panes.setContent(scrollPane);
						 							 } else {
						 							 	 Label l = new Label(newValue.getValue() + " is no related invariants");
						 							     l.setPadding(new Insets(8, 8, 8, 8));
						 							     invariants_panes.setContent(l);
						 							 }
						 							 
						 							 //reset pre- and post-conditions area color
						 							 precondition.setStyle("-fx-background-color:#FFFFFF; -fx-control-inner-background: #FFFFFF ");
						 							 postcondition.setStyle("-fx-background-color:#FFFFFF; -fx-control-inner-background: #FFFFFF");
						 							 //reset condition panel title
						 							 precondition_pane.setText("Precondition");
						 							 postcondition_pane.setText("Postcondition");
						 						} 
						 				);
		TreeItem<String> treeRoottrainer = new TreeItem<String>("Root node");
			TreeItem<String> subTreeRoot_create_Course = new TreeItem<String>("create_Course");
			subTreeRoot_create_Course.getChildren().addAll(Arrays.asList(			 		    
					 	new TreeItem<String>("requestCreateCourse"),
					 	new TreeItem<String>("createRoom"),
					 	new TreeItem<String>("confirmCourseInfo")
				 		));	
			TreeItem<String> subTreeRoot_update_schedule = new TreeItem<String>("update_schedule");
			subTreeRoot_update_schedule.getChildren().addAll(Arrays.asList(			 		    
					 	new TreeItem<String>("listCourseByTrainer"),
					 	new TreeItem<String>("chooseSpecificCourse"),
					 	new TreeItem<String>("confirmUpdateInfo")
				 		));	
			TreeItem<String> subTreeRoot_finish_Course = new TreeItem<String>("finish_Course");
			subTreeRoot_finish_Course.getChildren().addAll(Arrays.asList(			 		    
					 	new TreeItem<String>("listAllCourseByTrainer"),
					 	new TreeItem<String>("confirmCourseFinish"),
					 	new TreeItem<String>("requestBonus")
				 		));	
		
		treeRoottrainer.getChildren().addAll(Arrays.asList(
			subTreeRoot_create_Course,
			subTreeRoot_update_schedule,
			subTreeRoot_finish_Course
					));
		
		treeRoottrainer.setExpanded(true);

		actor_treeview_trainer.setShowRoot(false);
		actor_treeview_trainer.setRoot(treeRoottrainer);
		
		//TreeView click, then open the GridPane for inputing parameters
		actor_treeview_trainer.getSelectionModel().selectedItemProperty().addListener(
						 (observable, oldValue, newValue) -> { 
						 								
						 							 //clear the previous return
													 operation_return_pane.setContent(new Label());
													 
						 							 clickedOp = newValue.getValue();
						 							 GridPane op = operationPanels.get(clickedOp);
						 							 VBox vb = opInvariantPanel.get(clickedOp);
						 							 
						 							 //op pannel
						 							 if (op != null) {
						 								 operation_paras.setContent(operationPanels.get(newValue.getValue()));
						 								 
						 								 ObservableList<Node> l = operationPanels.get(newValue.getValue()).getChildren();
						 								 choosenOperation = new LinkedList<TextField>();
						 								 for (Node n : l) {
						 								 	 if (n instanceof TextField) {
						 								 	 	choosenOperation.add((TextField)n);
						 								 	  }
						 								 }
						 								 
						 								 definition.setText(definitions_map.get(newValue.getValue()));
						 								 precondition.setText(preconditions_map.get(newValue.getValue()));
						 								 postcondition.setText(postconditions_map.get(newValue.getValue()));
						 								 
						 						     }
						 							 else {
						 								 Label l = new Label(newValue.getValue() + " is no contract information in requirement model.");
						 								 l.setPadding(new Insets(8, 8, 8, 8));
						 								 operation_paras.setContent(l);
						 							 }	
						 							 
						 							 //op invariants
						 							 if (vb != null) {
						 							 	ScrollPane scrollPane = new ScrollPane(vb);
						 							 	scrollPane.setFitToWidth(true);
						 							 	invariants_panes.setMaxHeight(200); 
						 							 	//all_invariant_pane.setContent(scrollPane);	
						 							 	
						 							 	invariants_panes.setContent(scrollPane);
						 							 } else {
						 							 	 Label l = new Label(newValue.getValue() + " is no related invariants");
						 							     l.setPadding(new Insets(8, 8, 8, 8));
						 							     invariants_panes.setContent(l);
						 							 }
						 							 
						 							 //reset pre- and post-conditions area color
						 							 precondition.setStyle("-fx-background-color:#FFFFFF; -fx-control-inner-background: #FFFFFF ");
						 							 postcondition.setStyle("-fx-background-color:#FFFFFF; -fx-control-inner-background: #FFFFFF");
						 							 //reset condition panel title
						 							 precondition_pane.setText("Precondition");
						 							 postcondition_pane.setText("Postcondition");
						 						} 
						 				);
		TreeItem<String> treeRootfrontdesk = new TreeItem<String>("Root node");
			TreeItem<String> subTreeRoot_register_member = new TreeItem<String>("register_member");
			subTreeRoot_register_member.getChildren().addAll(Arrays.asList(			 		    
					 	new TreeItem<String>("input_member_info")
				 		));	
			TreeItem<String> subTreeRoot_register_trainer = new TreeItem<String>("register_trainer");
			subTreeRoot_register_trainer.getChildren().addAll(Arrays.asList(			 		    
					 	new TreeItem<String>("input_trainer_info")
				 		));	
		
		treeRootfrontdesk.getChildren().addAll(Arrays.asList(
			subTreeRoot_register_member,
			subTreeRoot_register_trainer
					));
		
		treeRootfrontdesk.setExpanded(true);

		actor_treeview_frontdesk.setShowRoot(false);
		actor_treeview_frontdesk.setRoot(treeRootfrontdesk);
		
		//TreeView click, then open the GridPane for inputing parameters
		actor_treeview_frontdesk.getSelectionModel().selectedItemProperty().addListener(
						 (observable, oldValue, newValue) -> { 
						 								
						 							 //clear the previous return
													 operation_return_pane.setContent(new Label());
													 
						 							 clickedOp = newValue.getValue();
						 							 GridPane op = operationPanels.get(clickedOp);
						 							 VBox vb = opInvariantPanel.get(clickedOp);
						 							 
						 							 //op pannel
						 							 if (op != null) {
						 								 operation_paras.setContent(operationPanels.get(newValue.getValue()));
						 								 
						 								 ObservableList<Node> l = operationPanels.get(newValue.getValue()).getChildren();
						 								 choosenOperation = new LinkedList<TextField>();
						 								 for (Node n : l) {
						 								 	 if (n instanceof TextField) {
						 								 	 	choosenOperation.add((TextField)n);
						 								 	  }
						 								 }
						 								 
						 								 definition.setText(definitions_map.get(newValue.getValue()));
						 								 precondition.setText(preconditions_map.get(newValue.getValue()));
						 								 postcondition.setText(postconditions_map.get(newValue.getValue()));
						 								 
						 						     }
						 							 else {
						 								 Label l = new Label(newValue.getValue() + " is no contract information in requirement model.");
						 								 l.setPadding(new Insets(8, 8, 8, 8));
						 								 operation_paras.setContent(l);
						 							 }	
						 							 
						 							 //op invariants
						 							 if (vb != null) {
						 							 	ScrollPane scrollPane = new ScrollPane(vb);
						 							 	scrollPane.setFitToWidth(true);
						 							 	invariants_panes.setMaxHeight(200); 
						 							 	//all_invariant_pane.setContent(scrollPane);	
						 							 	
						 							 	invariants_panes.setContent(scrollPane);
						 							 } else {
						 							 	 Label l = new Label(newValue.getValue() + " is no related invariants");
						 							     l.setPadding(new Insets(8, 8, 8, 8));
						 							     invariants_panes.setContent(l);
						 							 }
						 							 
						 							 //reset pre- and post-conditions area color
						 							 precondition.setStyle("-fx-background-color:#FFFFFF; -fx-control-inner-background: #FFFFFF ");
						 							 postcondition.setStyle("-fx-background-color:#FFFFFF; -fx-control-inner-background: #FFFFFF");
						 							 //reset condition panel title
						 							 precondition_pane.setText("Precondition");
						 							 postcondition_pane.setText("Postcondition");
						 						} 
						 				);
	}

	/**
	*    Execute Operation
	*/
	@FXML
	public void execute(ActionEvent event) {
		
		switch (clickedOp) {
		case "listAllCourseBooked" : listAllCourseBooked(); break;
		case "confirmCancelBook" : confirmCancelBook(); break;
		case "requestRefund" : requestRefund(); break;
		case "listAllCourseAvailable" : listAllCourseAvailable(); break;
		case "chooseOneBooking" : chooseOneBooking(); break;
		case "payFee" : payFee(); break;
		case "login_member" : login_member(); break;
		case "login_trainer" : login_trainer(); break;
		case "login_frontdesk" : login_frontdesk(); break;
		case "input_member_info" : input_member_info(); break;
		case "input_trainer_info" : input_trainer_info(); break;
		case "requestCreateCourse" : requestCreateCourse(); break;
		case "createRoom" : createRoom(); break;
		case "confirmCourseInfo" : confirmCourseInfo(); break;
		case "listCourseByTrainer" : listCourseByTrainer(); break;
		case "chooseSpecificCourse" : chooseSpecificCourse(); break;
		case "confirmUpdateInfo" : confirmUpdateInfo(); break;
		case "listAllCourseByTrainer" : listAllCourseByTrainer(); break;
		case "confirmCourseFinish" : confirmCourseFinish(); break;
		case "requestBonus" : requestBonus(); break;
		
		}
		
		System.out.println("execute buttion clicked");
		
		//checking relevant invariants
		opInvairantPanelUpdate();
	}

	/**
	*    Refresh All
	*/		
	@FXML
	public void refresh(ActionEvent event) {
		
		refreshAll();
		System.out.println("refresh all");
	}		
	
	/**
	*    Save All
	*/			
	@FXML
	public void save(ActionEvent event) {
		
		Stage stage = (Stage) mainPane.getScene().getWindow();
		
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save State to File");
		fileChooser.setInitialFileName("*.state");
		fileChooser.getExtensionFilters().addAll(
		         new ExtensionFilter("RMCode State File", "*.state"));
		
		File file = fileChooser.showSaveDialog(stage);
		
		if (file != null) {
			System.out.println("save state to file " + file.getAbsolutePath());				
			EntityManager.save(file);
		}
	}
	
	/**
	*    Load All
	*/			
	@FXML
	public void load(ActionEvent event) {
		
		Stage stage = (Stage) mainPane.getScene().getWindow();
		
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open State File");
		fileChooser.getExtensionFilters().addAll(
		         new ExtensionFilter("RMCode State File", "*.state"));
		
		File file = fileChooser.showOpenDialog(stage);
		
		if (file != null) {
			System.out.println("choose file" + file.getAbsolutePath());
			EntityManager.load(file); 
		}
		
		//refresh GUI after load data
		refreshAll();
	}
	
	
	//precondition unsat dialog
	public void preconditionUnSat() {
		
		Alert alert = new Alert(AlertType.WARNING, "");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(mainPane.getScene().getWindow());
        alert.getDialogPane().setContentText("Precondtion is not satisfied");
        alert.getDialogPane().setHeaderText(null);
        alert.showAndWait();	
	}
	
	//postcondition unsat dialog
	public void postconditionUnSat() {
		
		Alert alert = new Alert(AlertType.WARNING, "");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(mainPane.getScene().getWindow());
        alert.getDialogPane().setContentText("Postcondtion is not satisfied");
        alert.getDialogPane().setHeaderText(null);
        alert.showAndWait();	
	}

	public void thirdpartyServiceUnSat() {
		
		Alert alert = new Alert(AlertType.WARNING, "");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(mainPane.getScene().getWindow());
        alert.getDialogPane().setContentText("third party service is exception");
        alert.getDialogPane().setHeaderText(null);
        alert.showAndWait();	
	}		
	
	
	public void listAllCourseBooked() {
		
		System.out.println("execute listAllCourseBooked");
		String time = String.format("%1$tH:%1$tM:%1$tS", System.currentTimeMillis());
		log.appendText(time + " -- execute operation: listAllCourseBooked in service: Cancel_book_CourseService ");
		
		try {
			//invoke op with parameters
					List<CourseRecord> result = cancel_book_courseservice_service.listAllCourseBooked(
					listAllCourseBooked_member_id_t.getText()
					);
				
					//binding result to GUI
					TableView<Map<String, String>> tableCourseRecord = new TableView<Map<String, String>>();
					TableColumn<Map<String, String>, String> tableCourseRecord_Id = new TableColumn<Map<String, String>, String>("Id");
					tableCourseRecord_Id.setMinWidth("Id".length()*10);
					tableCourseRecord_Id.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
						@Override
					    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
					        return new ReadOnlyStringWrapper(data.getValue().get("Id"));
					    }
					});	
					tableCourseRecord.getColumns().add(tableCourseRecord_Id);
					TableColumn<Map<String, String>, String> tableCourseRecord_UserId = new TableColumn<Map<String, String>, String>("UserId");
					tableCourseRecord_UserId.setMinWidth("UserId".length()*10);
					tableCourseRecord_UserId.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
						@Override
					    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
					        return new ReadOnlyStringWrapper(data.getValue().get("UserId"));
					    }
					});	
					tableCourseRecord.getColumns().add(tableCourseRecord_UserId);
					TableColumn<Map<String, String>, String> tableCourseRecord_CourseId = new TableColumn<Map<String, String>, String>("CourseId");
					tableCourseRecord_CourseId.setMinWidth("CourseId".length()*10);
					tableCourseRecord_CourseId.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
						@Override
					    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
					        return new ReadOnlyStringWrapper(data.getValue().get("CourseId"));
					    }
					});	
					tableCourseRecord.getColumns().add(tableCourseRecord_CourseId);
					TableColumn<Map<String, String>, String> tableCourseRecord_Status = new TableColumn<Map<String, String>, String>("Status");
					tableCourseRecord_Status.setMinWidth("Status".length()*10);
					tableCourseRecord_Status.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
						@Override
					    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
					        return new ReadOnlyStringWrapper(data.getValue().get("Status"));
					    }
					});	
					tableCourseRecord.getColumns().add(tableCourseRecord_Status);
					
					ObservableList<Map<String, String>> dataCourseRecord = FXCollections.observableArrayList();
					for (CourseRecord r : result) {
						Map<String, String> unit = new HashMap<String, String>();
						if (r.getId() != null)
							unit.put("Id", String.valueOf(r.getId()));
						else
							unit.put("Id", "");
						if (r.getUserId() != null)
							unit.put("UserId", String.valueOf(r.getUserId()));
						else
							unit.put("UserId", "");
						if (r.getCourseId() != null)
							unit.put("CourseId", String.valueOf(r.getCourseId()));
						else
							unit.put("CourseId", "");
						unit.put("Status", String.valueOf(r.getStatus()));
						dataCourseRecord.add(unit);
					}
					
					tableCourseRecord.setItems(dataCourseRecord);
					operation_return_pane.setContent(tableCourseRecord);
				
			
			//return type is entity
		    log.appendText(" -- success!\n");
		    //set pre- and post-conditions text area color
		    precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #7CFC00");
		    postcondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #7CFC00");
		    //contract evaluation result
		    precondition_pane.setText("Precondition: True");
		    postcondition_pane.setText("Postcondition: True");
		    
		    
		} catch (PreconditionException e) {
			log.appendText(" -- failed!\n");
			precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");
			precondition_pane.setText("Precondition: False");	
			preconditionUnSat();
			
		} catch (PostconditionException e) {
			log.appendText(" -- failed!\n");
			postcondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");	
			postcondition_pane.setText("Postcondition: False");
			postconditionUnSat();
			
		} catch (NumberFormatException e) {
			log.appendText(" -- failed!\n");
			precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");
			precondition_pane.setText("Precondition: False");	
			preconditionUnSat();
			
		} catch (Exception e ) {		
			if (e instanceof ThirdPartyServiceException)
				thirdpartyServiceUnSat();
		}
	}
	public void confirmCancelBook() {
		
		System.out.println("execute confirmCancelBook");
		String time = String.format("%1$tH:%1$tM:%1$tS", System.currentTimeMillis());
		log.appendText(time + " -- execute operation: confirmCancelBook in service: Cancel_book_CourseService ");
		
		try {
			//invoke op with parameters
			//return value is primitive type, bind result to label.
			String result = String.valueOf(cancel_book_courseservice_service.confirmCancelBook(
			confirmCancelBook_course_id_t.getText(),
			confirmCancelBook_member_id_t.getText()
			));	
			Label l = new Label(result);
			l.setPadding(new Insets(8, 8, 8, 8));
			operation_return_pane.setContent(l);
		    log.appendText(" -- success!\n");
		    //set pre- and post-conditions text area color
		    precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #7CFC00");
		    postcondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #7CFC00");
		    //contract evaluation result
		    precondition_pane.setText("Precondition: True");
		    postcondition_pane.setText("Postcondition: True");
		    
		    
		} catch (PreconditionException e) {
			log.appendText(" -- failed!\n");
			precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");
			precondition_pane.setText("Precondition: False");	
			preconditionUnSat();
			
		} catch (PostconditionException e) {
			log.appendText(" -- failed!\n");
			postcondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");	
			postcondition_pane.setText("Postcondition: False");
			postconditionUnSat();
			
		} catch (NumberFormatException e) {
			log.appendText(" -- failed!\n");
			precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");
			precondition_pane.setText("Precondition: False");	
			preconditionUnSat();
			
		} catch (Exception e ) {		
			if (e instanceof ThirdPartyServiceException)
				thirdpartyServiceUnSat();
		}
	}
	public void requestRefund() {
		
		System.out.println("execute requestRefund");
		String time = String.format("%1$tH:%1$tM:%1$tS", System.currentTimeMillis());
		log.appendText(time + " -- execute operation: requestRefund in service: Cancel_book_CourseService ");
		
		try {
			//invoke op with parameters
			//return value is primitive type, bind result to label.
			String result = String.valueOf(cancel_book_courseservice_service.requestRefund(
			requestRefund_course_id_t.getText(),
			requestRefund_member_id_t.getText()
			));	
			Label l = new Label(result);
			l.setPadding(new Insets(8, 8, 8, 8));
			operation_return_pane.setContent(l);
		    log.appendText(" -- success!\n");
		    //set pre- and post-conditions text area color
		    precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #7CFC00");
		    postcondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #7CFC00");
		    //contract evaluation result
		    precondition_pane.setText("Precondition: True");
		    postcondition_pane.setText("Postcondition: True");
		    
		    
		} catch (PreconditionException e) {
			log.appendText(" -- failed!\n");
			precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");
			precondition_pane.setText("Precondition: False");	
			preconditionUnSat();
			
		} catch (PostconditionException e) {
			log.appendText(" -- failed!\n");
			postcondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");	
			postcondition_pane.setText("Postcondition: False");
			postconditionUnSat();
			
		} catch (NumberFormatException e) {
			log.appendText(" -- failed!\n");
			precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");
			precondition_pane.setText("Precondition: False");	
			preconditionUnSat();
			
		} catch (Exception e ) {		
			if (e instanceof ThirdPartyServiceException)
				thirdpartyServiceUnSat();
		}
	}
	public void listAllCourseAvailable() {
		
		System.out.println("execute listAllCourseAvailable");
		String time = String.format("%1$tH:%1$tM:%1$tS", System.currentTimeMillis());
		log.appendText(time + " -- execute operation: listAllCourseAvailable in service: Book_CourseService ");
		
		try {
			//invoke op with parameters
					List<Course> result = book_courseservice_service.listAllCourseAvailable(
					listAllCourseAvailable_member_id_t.getText()
					);
				
					//binding result to GUI
					TableView<Map<String, String>> tableCourse = new TableView<Map<String, String>>();
					TableColumn<Map<String, String>, String> tableCourse_Id = new TableColumn<Map<String, String>, String>("Id");
					tableCourse_Id.setMinWidth("Id".length()*10);
					tableCourse_Id.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
						@Override
					    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
					        return new ReadOnlyStringWrapper(data.getValue().get("Id"));
					    }
					});	
					tableCourse.getColumns().add(tableCourse_Id);
					TableColumn<Map<String, String>, String> tableCourse_Name = new TableColumn<Map<String, String>, String>("Name");
					tableCourse_Name.setMinWidth("Name".length()*10);
					tableCourse_Name.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
						@Override
					    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
					        return new ReadOnlyStringWrapper(data.getValue().get("Name"));
					    }
					});	
					tableCourse.getColumns().add(tableCourse_Name);
					TableColumn<Map<String, String>, String> tableCourse_TrainerId = new TableColumn<Map<String, String>, String>("TrainerId");
					tableCourse_TrainerId.setMinWidth("TrainerId".length()*10);
					tableCourse_TrainerId.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
						@Override
					    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
					        return new ReadOnlyStringWrapper(data.getValue().get("TrainerId"));
					    }
					});	
					tableCourse.getColumns().add(tableCourse_TrainerId);
					TableColumn<Map<String, String>, String> tableCourse_Description = new TableColumn<Map<String, String>, String>("Description");
					tableCourse_Description.setMinWidth("Description".length()*10);
					tableCourse_Description.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
						@Override
					    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
					        return new ReadOnlyStringWrapper(data.getValue().get("Description"));
					    }
					});	
					tableCourse.getColumns().add(tableCourse_Description);
					TableColumn<Map<String, String>, String> tableCourse_RegisterStartTime = new TableColumn<Map<String, String>, String>("RegisterStartTime");
					tableCourse_RegisterStartTime.setMinWidth("RegisterStartTime".length()*10);
					tableCourse_RegisterStartTime.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
						@Override
					    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
					        return new ReadOnlyStringWrapper(data.getValue().get("RegisterStartTime"));
					    }
					});	
					tableCourse.getColumns().add(tableCourse_RegisterStartTime);
					TableColumn<Map<String, String>, String> tableCourse_RegisterEndTime = new TableColumn<Map<String, String>, String>("RegisterEndTime");
					tableCourse_RegisterEndTime.setMinWidth("RegisterEndTime".length()*10);
					tableCourse_RegisterEndTime.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
						@Override
					    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
					        return new ReadOnlyStringWrapper(data.getValue().get("RegisterEndTime"));
					    }
					});	
					tableCourse.getColumns().add(tableCourse_RegisterEndTime);
					TableColumn<Map<String, String>, String> tableCourse_EventStartTime = new TableColumn<Map<String, String>, String>("EventStartTime");
					tableCourse_EventStartTime.setMinWidth("EventStartTime".length()*10);
					tableCourse_EventStartTime.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
						@Override
					    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
					        return new ReadOnlyStringWrapper(data.getValue().get("EventStartTime"));
					    }
					});	
					tableCourse.getColumns().add(tableCourse_EventStartTime);
					TableColumn<Map<String, String>, String> tableCourse_EventEndTime = new TableColumn<Map<String, String>, String>("EventEndTime");
					tableCourse_EventEndTime.setMinWidth("EventEndTime".length()*10);
					tableCourse_EventEndTime.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
						@Override
					    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
					        return new ReadOnlyStringWrapper(data.getValue().get("EventEndTime"));
					    }
					});	
					tableCourse.getColumns().add(tableCourse_EventEndTime);
					TableColumn<Map<String, String>, String> tableCourse_Cost = new TableColumn<Map<String, String>, String>("Cost");
					tableCourse_Cost.setMinWidth("Cost".length()*10);
					tableCourse_Cost.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
						@Override
					    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
					        return new ReadOnlyStringWrapper(data.getValue().get("Cost"));
					    }
					});	
					tableCourse.getColumns().add(tableCourse_Cost);
					TableColumn<Map<String, String>, String> tableCourse_RoomId = new TableColumn<Map<String, String>, String>("RoomId");
					tableCourse_RoomId.setMinWidth("RoomId".length()*10);
					tableCourse_RoomId.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
						@Override
					    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
					        return new ReadOnlyStringWrapper(data.getValue().get("RoomId"));
					    }
					});	
					tableCourse.getColumns().add(tableCourse_RoomId);
					TableColumn<Map<String, String>, String> tableCourse_Capacity = new TableColumn<Map<String, String>, String>("Capacity");
					tableCourse_Capacity.setMinWidth("Capacity".length()*10);
					tableCourse_Capacity.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
						@Override
					    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
					        return new ReadOnlyStringWrapper(data.getValue().get("Capacity"));
					    }
					});	
					tableCourse.getColumns().add(tableCourse_Capacity);
					TableColumn<Map<String, String>, String> tableCourse_Status = new TableColumn<Map<String, String>, String>("Status");
					tableCourse_Status.setMinWidth("Status".length()*10);
					tableCourse_Status.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
						@Override
					    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
					        return new ReadOnlyStringWrapper(data.getValue().get("Status"));
					    }
					});	
					tableCourse.getColumns().add(tableCourse_Status);
					
					ObservableList<Map<String, String>> dataCourse = FXCollections.observableArrayList();
					for (Course r : result) {
						Map<String, String> unit = new HashMap<String, String>();
						if (r.getId() != null)
							unit.put("Id", String.valueOf(r.getId()));
						else
							unit.put("Id", "");
						if (r.getName() != null)
							unit.put("Name", String.valueOf(r.getName()));
						else
							unit.put("Name", "");
						if (r.getTrainerId() != null)
							unit.put("TrainerId", String.valueOf(r.getTrainerId()));
						else
							unit.put("TrainerId", "");
						if (r.getDescription() != null)
							unit.put("Description", String.valueOf(r.getDescription()));
						else
							unit.put("Description", "");
						if (r.getRegisterStartTime() != null)
							unit.put("RegisterStartTime", String.valueOf(r.getRegisterStartTime()));
						else
							unit.put("RegisterStartTime", "");
						if (r.getRegisterEndTime() != null)
							unit.put("RegisterEndTime", String.valueOf(r.getRegisterEndTime()));
						else
							unit.put("RegisterEndTime", "");
						if (r.getEventStartTime() != null)
							unit.put("EventStartTime", String.valueOf(r.getEventStartTime()));
						else
							unit.put("EventStartTime", "");
						if (r.getEventEndTime() != null)
							unit.put("EventEndTime", String.valueOf(r.getEventEndTime()));
						else
							unit.put("EventEndTime", "");
						unit.put("Cost", String.valueOf(r.getCost()));
						if (r.getRoomId() != null)
							unit.put("RoomId", String.valueOf(r.getRoomId()));
						else
							unit.put("RoomId", "");
						unit.put("Capacity", String.valueOf(r.getCapacity()));
						unit.put("Status", String.valueOf(r.getStatus()));
						dataCourse.add(unit);
					}
					
					tableCourse.setItems(dataCourse);
					operation_return_pane.setContent(tableCourse);
				
			
			//return type is entity
		    log.appendText(" -- success!\n");
		    //set pre- and post-conditions text area color
		    precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #7CFC00");
		    postcondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #7CFC00");
		    //contract evaluation result
		    precondition_pane.setText("Precondition: True");
		    postcondition_pane.setText("Postcondition: True");
		    
		    
		} catch (PreconditionException e) {
			log.appendText(" -- failed!\n");
			precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");
			precondition_pane.setText("Precondition: False");	
			preconditionUnSat();
			
		} catch (PostconditionException e) {
			log.appendText(" -- failed!\n");
			postcondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");	
			postcondition_pane.setText("Postcondition: False");
			postconditionUnSat();
			
		} catch (NumberFormatException e) {
			log.appendText(" -- failed!\n");
			precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");
			precondition_pane.setText("Precondition: False");	
			preconditionUnSat();
			
		} catch (Exception e ) {		
			if (e instanceof ThirdPartyServiceException)
				thirdpartyServiceUnSat();
		}
	}
	public void chooseOneBooking() {
		
		System.out.println("execute chooseOneBooking");
		String time = String.format("%1$tH:%1$tM:%1$tS", System.currentTimeMillis());
		log.appendText(time + " -- execute operation: chooseOneBooking in service: Book_CourseService ");
		
		try {
			//invoke op with parameters
			//return value is primitive type, bind result to label.
			String result = String.valueOf(book_courseservice_service.chooseOneBooking(
			chooseOneBooking_member_id_t.getText(),
			chooseOneBooking_course_id_t.getText()
			));	
			Label l = new Label(result);
			l.setPadding(new Insets(8, 8, 8, 8));
			operation_return_pane.setContent(l);
		    log.appendText(" -- success!\n");
		    //set pre- and post-conditions text area color
		    precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #7CFC00");
		    postcondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #7CFC00");
		    //contract evaluation result
		    precondition_pane.setText("Precondition: True");
		    postcondition_pane.setText("Postcondition: True");
		    
		    
		} catch (PreconditionException e) {
			log.appendText(" -- failed!\n");
			precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");
			precondition_pane.setText("Precondition: False");	
			preconditionUnSat();
			
		} catch (PostconditionException e) {
			log.appendText(" -- failed!\n");
			postcondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");	
			postcondition_pane.setText("Postcondition: False");
			postconditionUnSat();
			
		} catch (NumberFormatException e) {
			log.appendText(" -- failed!\n");
			precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");
			precondition_pane.setText("Precondition: False");	
			preconditionUnSat();
			
		} catch (Exception e ) {		
			if (e instanceof ThirdPartyServiceException)
				thirdpartyServiceUnSat();
		}
	}
	public void payFee() {
		
		System.out.println("execute payFee");
		String time = String.format("%1$tH:%1$tM:%1$tS", System.currentTimeMillis());
		log.appendText(time + " -- execute operation: payFee in service: Book_CourseService ");
		
		try {
			//invoke op with parameters
			//return value is primitive type, bind result to label.
			String result = String.valueOf(book_courseservice_service.payFee(
			payFee_member_id_t.getText(),
			payFee_course_id_t.getText(),
			payFee_datetime_t.getText()
			));	
			Label l = new Label(result);
			l.setPadding(new Insets(8, 8, 8, 8));
			operation_return_pane.setContent(l);
		    log.appendText(" -- success!\n");
		    //set pre- and post-conditions text area color
		    precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #7CFC00");
		    postcondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #7CFC00");
		    //contract evaluation result
		    precondition_pane.setText("Precondition: True");
		    postcondition_pane.setText("Postcondition: True");
		    
		    
		} catch (PreconditionException e) {
			log.appendText(" -- failed!\n");
			precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");
			precondition_pane.setText("Precondition: False");	
			preconditionUnSat();
			
		} catch (PostconditionException e) {
			log.appendText(" -- failed!\n");
			postcondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");	
			postcondition_pane.setText("Postcondition: False");
			postconditionUnSat();
			
		} catch (NumberFormatException e) {
			log.appendText(" -- failed!\n");
			precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");
			precondition_pane.setText("Precondition: False");	
			preconditionUnSat();
			
		} catch (Exception e ) {		
			if (e instanceof ThirdPartyServiceException)
				thirdpartyServiceUnSat();
		}
	}
	public void login_member() {
		
		System.out.println("execute login_member");
		String time = String.format("%1$tH:%1$tM:%1$tS", System.currentTimeMillis());
		log.appendText(time + " -- execute operation: login_member in service: LoginService ");
		
		try {
			//invoke op with parameters
			//return value is primitive type, bind result to label.
			String result = String.valueOf(loginservice_service.login_member(
			login_member_phone_t.getText(),
			login_member_password_t.getText()
			));	
			Label l = new Label(result);
			l.setPadding(new Insets(8, 8, 8, 8));
			operation_return_pane.setContent(l);
		    log.appendText(" -- success!\n");
		    //set pre- and post-conditions text area color
		    precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #7CFC00");
		    postcondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #7CFC00");
		    //contract evaluation result
		    precondition_pane.setText("Precondition: True");
		    postcondition_pane.setText("Postcondition: True");
		    
		    
		} catch (PreconditionException e) {
			log.appendText(" -- failed!\n");
			precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");
			precondition_pane.setText("Precondition: False");	
			preconditionUnSat();
			
		} catch (PostconditionException e) {
			log.appendText(" -- failed!\n");
			postcondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");	
			postcondition_pane.setText("Postcondition: False");
			postconditionUnSat();
			
		} catch (NumberFormatException e) {
			log.appendText(" -- failed!\n");
			precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");
			precondition_pane.setText("Precondition: False");	
			preconditionUnSat();
			
		} catch (Exception e ) {		
			if (e instanceof ThirdPartyServiceException)
				thirdpartyServiceUnSat();
		}
	}
	public void login_trainer() {
		
		System.out.println("execute login_trainer");
		String time = String.format("%1$tH:%1$tM:%1$tS", System.currentTimeMillis());
		log.appendText(time + " -- execute operation: login_trainer in service: LoginService ");
		
		try {
			//invoke op with parameters
			//return value is primitive type, bind result to label.
			String result = String.valueOf(loginservice_service.login_trainer(
			login_trainer_phone_t.getText(),
			login_trainer_password_t.getText()
			));	
			Label l = new Label(result);
			l.setPadding(new Insets(8, 8, 8, 8));
			operation_return_pane.setContent(l);
		    log.appendText(" -- success!\n");
		    //set pre- and post-conditions text area color
		    precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #7CFC00");
		    postcondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #7CFC00");
		    //contract evaluation result
		    precondition_pane.setText("Precondition: True");
		    postcondition_pane.setText("Postcondition: True");
		    
		    
		} catch (PreconditionException e) {
			log.appendText(" -- failed!\n");
			precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");
			precondition_pane.setText("Precondition: False");	
			preconditionUnSat();
			
		} catch (PostconditionException e) {
			log.appendText(" -- failed!\n");
			postcondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");	
			postcondition_pane.setText("Postcondition: False");
			postconditionUnSat();
			
		} catch (NumberFormatException e) {
			log.appendText(" -- failed!\n");
			precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");
			precondition_pane.setText("Precondition: False");	
			preconditionUnSat();
			
		} catch (Exception e ) {		
			if (e instanceof ThirdPartyServiceException)
				thirdpartyServiceUnSat();
		}
	}
	public void login_frontdesk() {
		
		System.out.println("execute login_frontdesk");
		String time = String.format("%1$tH:%1$tM:%1$tS", System.currentTimeMillis());
		log.appendText(time + " -- execute operation: login_frontdesk in service: LoginService ");
		
		try {
			//invoke op with parameters
			//return value is primitive type, bind result to label.
			String result = String.valueOf(loginservice_service.login_frontdesk(
			login_frontdesk_phone_t.getText(),
			login_frontdesk_password_t.getText()
			));	
			Label l = new Label(result);
			l.setPadding(new Insets(8, 8, 8, 8));
			operation_return_pane.setContent(l);
		    log.appendText(" -- success!\n");
		    //set pre- and post-conditions text area color
		    precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #7CFC00");
		    postcondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #7CFC00");
		    //contract evaluation result
		    precondition_pane.setText("Precondition: True");
		    postcondition_pane.setText("Postcondition: True");
		    
		    
		} catch (PreconditionException e) {
			log.appendText(" -- failed!\n");
			precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");
			precondition_pane.setText("Precondition: False");	
			preconditionUnSat();
			
		} catch (PostconditionException e) {
			log.appendText(" -- failed!\n");
			postcondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");	
			postcondition_pane.setText("Postcondition: False");
			postconditionUnSat();
			
		} catch (NumberFormatException e) {
			log.appendText(" -- failed!\n");
			precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");
			precondition_pane.setText("Precondition: False");	
			preconditionUnSat();
			
		} catch (Exception e ) {		
			if (e instanceof ThirdPartyServiceException)
				thirdpartyServiceUnSat();
		}
	}
	public void input_member_info() {
		
		System.out.println("execute input_member_info");
		String time = String.format("%1$tH:%1$tM:%1$tS", System.currentTimeMillis());
		log.appendText(time + " -- execute operation: input_member_info in service: Register_memberService ");
		
		try {
			//invoke op with parameters
			//return value is primitive type, bind result to label.
			String result = String.valueOf(register_memberservice_service.input_member_info(
			input_member_info_id_t.getText(),
			input_member_info_name_t.getText(),
			Integer.valueOf(input_member_info_age_t.getText()),
			input_member_info_phone_t.getText(),
			input_member_info_description_t.getText(),
			input_member_info_datetime_t.getText()
			));	
			Label l = new Label(result);
			l.setPadding(new Insets(8, 8, 8, 8));
			operation_return_pane.setContent(l);
		    log.appendText(" -- success!\n");
		    //set pre- and post-conditions text area color
		    precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #7CFC00");
		    postcondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #7CFC00");
		    //contract evaluation result
		    precondition_pane.setText("Precondition: True");
		    postcondition_pane.setText("Postcondition: True");
		    
		    
		} catch (PreconditionException e) {
			log.appendText(" -- failed!\n");
			precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");
			precondition_pane.setText("Precondition: False");	
			preconditionUnSat();
			
		} catch (PostconditionException e) {
			log.appendText(" -- failed!\n");
			postcondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");	
			postcondition_pane.setText("Postcondition: False");
			postconditionUnSat();
			
		} catch (NumberFormatException e) {
			log.appendText(" -- failed!\n");
			precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");
			precondition_pane.setText("Precondition: False");	
			preconditionUnSat();
			
		} catch (Exception e ) {		
			if (e instanceof ThirdPartyServiceException)
				thirdpartyServiceUnSat();
		}
	}
	public void input_trainer_info() {
		
		System.out.println("execute input_trainer_info");
		String time = String.format("%1$tH:%1$tM:%1$tS", System.currentTimeMillis());
		log.appendText(time + " -- execute operation: input_trainer_info in service: Register_trainerService ");
		
		try {
			//invoke op with parameters
			//return value is primitive type, bind result to label.
			String result = String.valueOf(register_trainerservice_service.input_trainer_info(
			input_trainer_info_id_t.getText(),
			input_trainer_info_name_t.getText(),
			Integer.valueOf(input_trainer_info_age_t.getText()),
			input_trainer_info_phone_t.getText(),
			input_trainer_info_description_t.getText(),
			input_trainer_info_datetime_t.getText()
			));	
			Label l = new Label(result);
			l.setPadding(new Insets(8, 8, 8, 8));
			operation_return_pane.setContent(l);
		    log.appendText(" -- success!\n");
		    //set pre- and post-conditions text area color
		    precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #7CFC00");
		    postcondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #7CFC00");
		    //contract evaluation result
		    precondition_pane.setText("Precondition: True");
		    postcondition_pane.setText("Postcondition: True");
		    
		    
		} catch (PreconditionException e) {
			log.appendText(" -- failed!\n");
			precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");
			precondition_pane.setText("Precondition: False");	
			preconditionUnSat();
			
		} catch (PostconditionException e) {
			log.appendText(" -- failed!\n");
			postcondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");	
			postcondition_pane.setText("Postcondition: False");
			postconditionUnSat();
			
		} catch (NumberFormatException e) {
			log.appendText(" -- failed!\n");
			precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");
			precondition_pane.setText("Precondition: False");	
			preconditionUnSat();
			
		} catch (Exception e ) {		
			if (e instanceof ThirdPartyServiceException)
				thirdpartyServiceUnSat();
		}
	}
	public void requestCreateCourse() {
		
		System.out.println("execute requestCreateCourse");
		String time = String.format("%1$tH:%1$tM:%1$tS", System.currentTimeMillis());
		log.appendText(time + " -- execute operation: requestCreateCourse in service: Create_CourseService ");
		
		try {
			//invoke op with parameters
			//return value is primitive type, bind result to label.
			String result = String.valueOf(create_courseservice_service.requestCreateCourse(
			requestCreateCourse_trainer_id_t.getText()
			));	
			Label l = new Label(result);
			l.setPadding(new Insets(8, 8, 8, 8));
			operation_return_pane.setContent(l);
		    log.appendText(" -- success!\n");
		    //set pre- and post-conditions text area color
		    precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #7CFC00");
		    postcondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #7CFC00");
		    //contract evaluation result
		    precondition_pane.setText("Precondition: True");
		    postcondition_pane.setText("Postcondition: True");
		    
		    
		} catch (PreconditionException e) {
			log.appendText(" -- failed!\n");
			precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");
			precondition_pane.setText("Precondition: False");	
			preconditionUnSat();
			
		} catch (PostconditionException e) {
			log.appendText(" -- failed!\n");
			postcondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");	
			postcondition_pane.setText("Postcondition: False");
			postconditionUnSat();
			
		} catch (NumberFormatException e) {
			log.appendText(" -- failed!\n");
			precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");
			precondition_pane.setText("Precondition: False");	
			preconditionUnSat();
			
		} catch (Exception e ) {		
			if (e instanceof ThirdPartyServiceException)
				thirdpartyServiceUnSat();
		}
	}
	public void createRoom() {
		
		System.out.println("execute createRoom");
		String time = String.format("%1$tH:%1$tM:%1$tS", System.currentTimeMillis());
		log.appendText(time + " -- execute operation: createRoom in service: Create_CourseService ");
		
		try {
			//invoke op with parameters
			//return value is primitive type, bind result to label.
			String result = String.valueOf(create_courseservice_service.createRoom(
			createRoom_room_id_t.getText(),
			createRoom_location_t.getText(),
			Integer.valueOf(createRoom_capacity_t.getText())
			));	
			Label l = new Label(result);
			l.setPadding(new Insets(8, 8, 8, 8));
			operation_return_pane.setContent(l);
		    log.appendText(" -- success!\n");
		    //set pre- and post-conditions text area color
		    precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #7CFC00");
		    postcondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #7CFC00");
		    //contract evaluation result
		    precondition_pane.setText("Precondition: True");
		    postcondition_pane.setText("Postcondition: True");
		    
		    
		} catch (PreconditionException e) {
			log.appendText(" -- failed!\n");
			precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");
			precondition_pane.setText("Precondition: False");	
			preconditionUnSat();
			
		} catch (PostconditionException e) {
			log.appendText(" -- failed!\n");
			postcondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");	
			postcondition_pane.setText("Postcondition: False");
			postconditionUnSat();
			
		} catch (NumberFormatException e) {
			log.appendText(" -- failed!\n");
			precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");
			precondition_pane.setText("Precondition: False");	
			preconditionUnSat();
			
		} catch (Exception e ) {		
			if (e instanceof ThirdPartyServiceException)
				thirdpartyServiceUnSat();
		}
	}
	public void confirmCourseInfo() {
		
		System.out.println("execute confirmCourseInfo");
		String time = String.format("%1$tH:%1$tM:%1$tS", System.currentTimeMillis());
		log.appendText(time + " -- execute operation: confirmCourseInfo in service: Create_CourseService ");
		
		try {
			//invoke op with parameters
			//return value is primitive type, bind result to label.
			String result = String.valueOf(create_courseservice_service.confirmCourseInfo(
			confirmCourseInfo_room_id_t.getText(),
			confirmCourseInfo_course_id_t.getText(),
			confirmCourseInfo_register_time_t.getText(),
			confirmCourseInfo_register_end_time_t.getText(),
			confirmCourseInfo_begin_time_t.getText(),
			confirmCourseInfo_end_time_t.getText(),
			confirmCourseInfo_course_name_t.getText(),
			confirmCourseInfo_description_t.getText(),
			confirmCourseInfo_trainer_id_t.getText(),
			Float.valueOf(confirmCourseInfo_cost_t.getText())
			));	
			Label l = new Label(result);
			l.setPadding(new Insets(8, 8, 8, 8));
			operation_return_pane.setContent(l);
		    log.appendText(" -- success!\n");
		    //set pre- and post-conditions text area color
		    precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #7CFC00");
		    postcondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #7CFC00");
		    //contract evaluation result
		    precondition_pane.setText("Precondition: True");
		    postcondition_pane.setText("Postcondition: True");
		    
		    
		} catch (PreconditionException e) {
			log.appendText(" -- failed!\n");
			precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");
			precondition_pane.setText("Precondition: False");	
			preconditionUnSat();
			
		} catch (PostconditionException e) {
			log.appendText(" -- failed!\n");
			postcondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");	
			postcondition_pane.setText("Postcondition: False");
			postconditionUnSat();
			
		} catch (NumberFormatException e) {
			log.appendText(" -- failed!\n");
			precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");
			precondition_pane.setText("Precondition: False");	
			preconditionUnSat();
			
		} catch (Exception e ) {		
			if (e instanceof ThirdPartyServiceException)
				thirdpartyServiceUnSat();
		}
	}
	public void listCourseByTrainer() {
		
		System.out.println("execute listCourseByTrainer");
		String time = String.format("%1$tH:%1$tM:%1$tS", System.currentTimeMillis());
		log.appendText(time + " -- execute operation: listCourseByTrainer in service: Update_scheduleService ");
		
		try {
			//invoke op with parameters
			//return value is primitive type, bind result to label.
			String result = String.valueOf(update_scheduleservice_service.listCourseByTrainer(
			listCourseByTrainer_trainer_id_t.getText()
			));	
			Label l = new Label(result);
			l.setPadding(new Insets(8, 8, 8, 8));
			operation_return_pane.setContent(l);
		    log.appendText(" -- success!\n");
		    //set pre- and post-conditions text area color
		    precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #7CFC00");
		    postcondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #7CFC00");
		    //contract evaluation result
		    precondition_pane.setText("Precondition: True");
		    postcondition_pane.setText("Postcondition: True");
		    
		    
		} catch (PreconditionException e) {
			log.appendText(" -- failed!\n");
			precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");
			precondition_pane.setText("Precondition: False");	
			preconditionUnSat();
			
		} catch (PostconditionException e) {
			log.appendText(" -- failed!\n");
			postcondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");	
			postcondition_pane.setText("Postcondition: False");
			postconditionUnSat();
			
		} catch (NumberFormatException e) {
			log.appendText(" -- failed!\n");
			precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");
			precondition_pane.setText("Precondition: False");	
			preconditionUnSat();
			
		} catch (Exception e ) {		
			if (e instanceof ThirdPartyServiceException)
				thirdpartyServiceUnSat();
		}
	}
	public void chooseSpecificCourse() {
		
		System.out.println("execute chooseSpecificCourse");
		String time = String.format("%1$tH:%1$tM:%1$tS", System.currentTimeMillis());
		log.appendText(time + " -- execute operation: chooseSpecificCourse in service: Update_scheduleService ");
		
		try {
			//invoke op with parameters
			//return value is primitive type, bind result to label.
			String result = String.valueOf(update_scheduleservice_service.chooseSpecificCourse(
			chooseSpecificCourse_trainer_id_t.getText(),
			chooseSpecificCourse_course_id_t.getText()
			));	
			Label l = new Label(result);
			l.setPadding(new Insets(8, 8, 8, 8));
			operation_return_pane.setContent(l);
		    log.appendText(" -- success!\n");
		    //set pre- and post-conditions text area color
		    precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #7CFC00");
		    postcondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #7CFC00");
		    //contract evaluation result
		    precondition_pane.setText("Precondition: True");
		    postcondition_pane.setText("Postcondition: True");
		    
		    
		} catch (PreconditionException e) {
			log.appendText(" -- failed!\n");
			precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");
			precondition_pane.setText("Precondition: False");	
			preconditionUnSat();
			
		} catch (PostconditionException e) {
			log.appendText(" -- failed!\n");
			postcondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");	
			postcondition_pane.setText("Postcondition: False");
			postconditionUnSat();
			
		} catch (NumberFormatException e) {
			log.appendText(" -- failed!\n");
			precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");
			precondition_pane.setText("Precondition: False");	
			preconditionUnSat();
			
		} catch (Exception e ) {		
			if (e instanceof ThirdPartyServiceException)
				thirdpartyServiceUnSat();
		}
	}
	public void confirmUpdateInfo() {
		
		System.out.println("execute confirmUpdateInfo");
		String time = String.format("%1$tH:%1$tM:%1$tS", System.currentTimeMillis());
		log.appendText(time + " -- execute operation: confirmUpdateInfo in service: Update_scheduleService ");
		
		try {
			//invoke op with parameters
			//return value is primitive type, bind result to label.
			String result = String.valueOf(update_scheduleservice_service.confirmUpdateInfo(
			confirmUpdateInfo_course_id_t.getText(),
			confirmUpdateInfo_course_name_t.getText(),
			confirmUpdateInfo_begin_time_t.getText(),
			confirmUpdateInfo_end_time_t.getText(),
			confirmUpdateInfo_description_t.getText(),
			confirmUpdateInfo_trainer_id_t.getText()
			));	
			Label l = new Label(result);
			l.setPadding(new Insets(8, 8, 8, 8));
			operation_return_pane.setContent(l);
		    log.appendText(" -- success!\n");
		    //set pre- and post-conditions text area color
		    precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #7CFC00");
		    postcondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #7CFC00");
		    //contract evaluation result
		    precondition_pane.setText("Precondition: True");
		    postcondition_pane.setText("Postcondition: True");
		    
		    
		} catch (PreconditionException e) {
			log.appendText(" -- failed!\n");
			precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");
			precondition_pane.setText("Precondition: False");	
			preconditionUnSat();
			
		} catch (PostconditionException e) {
			log.appendText(" -- failed!\n");
			postcondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");	
			postcondition_pane.setText("Postcondition: False");
			postconditionUnSat();
			
		} catch (NumberFormatException e) {
			log.appendText(" -- failed!\n");
			precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");
			precondition_pane.setText("Precondition: False");	
			preconditionUnSat();
			
		} catch (Exception e ) {		
			if (e instanceof ThirdPartyServiceException)
				thirdpartyServiceUnSat();
		}
	}
	public void listAllCourseByTrainer() {
		
		System.out.println("execute listAllCourseByTrainer");
		String time = String.format("%1$tH:%1$tM:%1$tS", System.currentTimeMillis());
		log.appendText(time + " -- execute operation: listAllCourseByTrainer in service: Finish_CourseService ");
		
		try {
			//invoke op with parameters
					List<Course> result = finish_courseservice_service.listAllCourseByTrainer(
					listAllCourseByTrainer_trainer_id_t.getText()
					);
				
					//binding result to GUI
					TableView<Map<String, String>> tableCourse = new TableView<Map<String, String>>();
					TableColumn<Map<String, String>, String> tableCourse_Id = new TableColumn<Map<String, String>, String>("Id");
					tableCourse_Id.setMinWidth("Id".length()*10);
					tableCourse_Id.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
						@Override
					    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
					        return new ReadOnlyStringWrapper(data.getValue().get("Id"));
					    }
					});	
					tableCourse.getColumns().add(tableCourse_Id);
					TableColumn<Map<String, String>, String> tableCourse_Name = new TableColumn<Map<String, String>, String>("Name");
					tableCourse_Name.setMinWidth("Name".length()*10);
					tableCourse_Name.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
						@Override
					    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
					        return new ReadOnlyStringWrapper(data.getValue().get("Name"));
					    }
					});	
					tableCourse.getColumns().add(tableCourse_Name);
					TableColumn<Map<String, String>, String> tableCourse_TrainerId = new TableColumn<Map<String, String>, String>("TrainerId");
					tableCourse_TrainerId.setMinWidth("TrainerId".length()*10);
					tableCourse_TrainerId.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
						@Override
					    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
					        return new ReadOnlyStringWrapper(data.getValue().get("TrainerId"));
					    }
					});	
					tableCourse.getColumns().add(tableCourse_TrainerId);
					TableColumn<Map<String, String>, String> tableCourse_Description = new TableColumn<Map<String, String>, String>("Description");
					tableCourse_Description.setMinWidth("Description".length()*10);
					tableCourse_Description.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
						@Override
					    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
					        return new ReadOnlyStringWrapper(data.getValue().get("Description"));
					    }
					});	
					tableCourse.getColumns().add(tableCourse_Description);
					TableColumn<Map<String, String>, String> tableCourse_RegisterStartTime = new TableColumn<Map<String, String>, String>("RegisterStartTime");
					tableCourse_RegisterStartTime.setMinWidth("RegisterStartTime".length()*10);
					tableCourse_RegisterStartTime.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
						@Override
					    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
					        return new ReadOnlyStringWrapper(data.getValue().get("RegisterStartTime"));
					    }
					});	
					tableCourse.getColumns().add(tableCourse_RegisterStartTime);
					TableColumn<Map<String, String>, String> tableCourse_RegisterEndTime = new TableColumn<Map<String, String>, String>("RegisterEndTime");
					tableCourse_RegisterEndTime.setMinWidth("RegisterEndTime".length()*10);
					tableCourse_RegisterEndTime.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
						@Override
					    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
					        return new ReadOnlyStringWrapper(data.getValue().get("RegisterEndTime"));
					    }
					});	
					tableCourse.getColumns().add(tableCourse_RegisterEndTime);
					TableColumn<Map<String, String>, String> tableCourse_EventStartTime = new TableColumn<Map<String, String>, String>("EventStartTime");
					tableCourse_EventStartTime.setMinWidth("EventStartTime".length()*10);
					tableCourse_EventStartTime.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
						@Override
					    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
					        return new ReadOnlyStringWrapper(data.getValue().get("EventStartTime"));
					    }
					});	
					tableCourse.getColumns().add(tableCourse_EventStartTime);
					TableColumn<Map<String, String>, String> tableCourse_EventEndTime = new TableColumn<Map<String, String>, String>("EventEndTime");
					tableCourse_EventEndTime.setMinWidth("EventEndTime".length()*10);
					tableCourse_EventEndTime.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
						@Override
					    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
					        return new ReadOnlyStringWrapper(data.getValue().get("EventEndTime"));
					    }
					});	
					tableCourse.getColumns().add(tableCourse_EventEndTime);
					TableColumn<Map<String, String>, String> tableCourse_Cost = new TableColumn<Map<String, String>, String>("Cost");
					tableCourse_Cost.setMinWidth("Cost".length()*10);
					tableCourse_Cost.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
						@Override
					    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
					        return new ReadOnlyStringWrapper(data.getValue().get("Cost"));
					    }
					});	
					tableCourse.getColumns().add(tableCourse_Cost);
					TableColumn<Map<String, String>, String> tableCourse_RoomId = new TableColumn<Map<String, String>, String>("RoomId");
					tableCourse_RoomId.setMinWidth("RoomId".length()*10);
					tableCourse_RoomId.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
						@Override
					    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
					        return new ReadOnlyStringWrapper(data.getValue().get("RoomId"));
					    }
					});	
					tableCourse.getColumns().add(tableCourse_RoomId);
					TableColumn<Map<String, String>, String> tableCourse_Capacity = new TableColumn<Map<String, String>, String>("Capacity");
					tableCourse_Capacity.setMinWidth("Capacity".length()*10);
					tableCourse_Capacity.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
						@Override
					    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
					        return new ReadOnlyStringWrapper(data.getValue().get("Capacity"));
					    }
					});	
					tableCourse.getColumns().add(tableCourse_Capacity);
					TableColumn<Map<String, String>, String> tableCourse_Status = new TableColumn<Map<String, String>, String>("Status");
					tableCourse_Status.setMinWidth("Status".length()*10);
					tableCourse_Status.setCellValueFactory(new Callback<CellDataFeatures<Map<String, String>, String>, ObservableValue<String>>() {	   
						@Override
					    public ObservableValue<String> call(CellDataFeatures<Map<String, String>, String> data) {
					        return new ReadOnlyStringWrapper(data.getValue().get("Status"));
					    }
					});	
					tableCourse.getColumns().add(tableCourse_Status);
					
					ObservableList<Map<String, String>> dataCourse = FXCollections.observableArrayList();
					for (Course r : result) {
						Map<String, String> unit = new HashMap<String, String>();
						if (r.getId() != null)
							unit.put("Id", String.valueOf(r.getId()));
						else
							unit.put("Id", "");
						if (r.getName() != null)
							unit.put("Name", String.valueOf(r.getName()));
						else
							unit.put("Name", "");
						if (r.getTrainerId() != null)
							unit.put("TrainerId", String.valueOf(r.getTrainerId()));
						else
							unit.put("TrainerId", "");
						if (r.getDescription() != null)
							unit.put("Description", String.valueOf(r.getDescription()));
						else
							unit.put("Description", "");
						if (r.getRegisterStartTime() != null)
							unit.put("RegisterStartTime", String.valueOf(r.getRegisterStartTime()));
						else
							unit.put("RegisterStartTime", "");
						if (r.getRegisterEndTime() != null)
							unit.put("RegisterEndTime", String.valueOf(r.getRegisterEndTime()));
						else
							unit.put("RegisterEndTime", "");
						if (r.getEventStartTime() != null)
							unit.put("EventStartTime", String.valueOf(r.getEventStartTime()));
						else
							unit.put("EventStartTime", "");
						if (r.getEventEndTime() != null)
							unit.put("EventEndTime", String.valueOf(r.getEventEndTime()));
						else
							unit.put("EventEndTime", "");
						unit.put("Cost", String.valueOf(r.getCost()));
						if (r.getRoomId() != null)
							unit.put("RoomId", String.valueOf(r.getRoomId()));
						else
							unit.put("RoomId", "");
						unit.put("Capacity", String.valueOf(r.getCapacity()));
						unit.put("Status", String.valueOf(r.getStatus()));
						dataCourse.add(unit);
					}
					
					tableCourse.setItems(dataCourse);
					operation_return_pane.setContent(tableCourse);
				
			
			//return type is entity
		    log.appendText(" -- success!\n");
		    //set pre- and post-conditions text area color
		    precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #7CFC00");
		    postcondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #7CFC00");
		    //contract evaluation result
		    precondition_pane.setText("Precondition: True");
		    postcondition_pane.setText("Postcondition: True");
		    
		    
		} catch (PreconditionException e) {
			log.appendText(" -- failed!\n");
			precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");
			precondition_pane.setText("Precondition: False");	
			preconditionUnSat();
			
		} catch (PostconditionException e) {
			log.appendText(" -- failed!\n");
			postcondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");	
			postcondition_pane.setText("Postcondition: False");
			postconditionUnSat();
			
		} catch (NumberFormatException e) {
			log.appendText(" -- failed!\n");
			precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");
			precondition_pane.setText("Precondition: False");	
			preconditionUnSat();
			
		} catch (Exception e ) {		
			if (e instanceof ThirdPartyServiceException)
				thirdpartyServiceUnSat();
		}
	}
	public void confirmCourseFinish() {
		
		System.out.println("execute confirmCourseFinish");
		String time = String.format("%1$tH:%1$tM:%1$tS", System.currentTimeMillis());
		log.appendText(time + " -- execute operation: confirmCourseFinish in service: Finish_CourseService ");
		
		try {
			//invoke op with parameters
			//return value is primitive type, bind result to label.
			String result = String.valueOf(finish_courseservice_service.confirmCourseFinish(
			confirmCourseFinish_trainer_id_t.getText(),
			confirmCourseFinish_course_id_t.getText()
			));	
			Label l = new Label(result);
			l.setPadding(new Insets(8, 8, 8, 8));
			operation_return_pane.setContent(l);
		    log.appendText(" -- success!\n");
		    //set pre- and post-conditions text area color
		    precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #7CFC00");
		    postcondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #7CFC00");
		    //contract evaluation result
		    precondition_pane.setText("Precondition: True");
		    postcondition_pane.setText("Postcondition: True");
		    
		    
		} catch (PreconditionException e) {
			log.appendText(" -- failed!\n");
			precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");
			precondition_pane.setText("Precondition: False");	
			preconditionUnSat();
			
		} catch (PostconditionException e) {
			log.appendText(" -- failed!\n");
			postcondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");	
			postcondition_pane.setText("Postcondition: False");
			postconditionUnSat();
			
		} catch (NumberFormatException e) {
			log.appendText(" -- failed!\n");
			precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");
			precondition_pane.setText("Precondition: False");	
			preconditionUnSat();
			
		} catch (Exception e ) {		
			if (e instanceof ThirdPartyServiceException)
				thirdpartyServiceUnSat();
		}
	}
	public void requestBonus() {
		
		System.out.println("execute requestBonus");
		String time = String.format("%1$tH:%1$tM:%1$tS", System.currentTimeMillis());
		log.appendText(time + " -- execute operation: requestBonus in service: Finish_CourseService ");
		
		try {
			//invoke op with parameters
			//return value is primitive type, bind result to label.
			String result = String.valueOf(finish_courseservice_service.requestBonus(
			requestBonus_trainer_id_t.getText(),
			requestBonus_course_id_t.getText()
			));	
			Label l = new Label(result);
			l.setPadding(new Insets(8, 8, 8, 8));
			operation_return_pane.setContent(l);
		    log.appendText(" -- success!\n");
		    //set pre- and post-conditions text area color
		    precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #7CFC00");
		    postcondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #7CFC00");
		    //contract evaluation result
		    precondition_pane.setText("Precondition: True");
		    postcondition_pane.setText("Postcondition: True");
		    
		    
		} catch (PreconditionException e) {
			log.appendText(" -- failed!\n");
			precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");
			precondition_pane.setText("Precondition: False");	
			preconditionUnSat();
			
		} catch (PostconditionException e) {
			log.appendText(" -- failed!\n");
			postcondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");	
			postcondition_pane.setText("Postcondition: False");
			postconditionUnSat();
			
		} catch (NumberFormatException e) {
			log.appendText(" -- failed!\n");
			precondition.setStyle("-fx-background-color:#000000; -fx-control-inner-background: #FF0000");
			precondition_pane.setText("Precondition: False");	
			preconditionUnSat();
			
		} catch (Exception e ) {		
			if (e instanceof ThirdPartyServiceException)
				thirdpartyServiceUnSat();
		}
	}




	//select object index
	int objectindex;
	
	@FXML
	TabPane mainPane;

	@FXML
	TextArea log;
	
	@FXML
	TreeView<String> actor_treeview_member;
	@FXML
	TreeView<String> actor_treeview_trainer;
	@FXML
	TreeView<String> actor_treeview_frontdesk;
	
	@FXML
	TreeView<String> actor_treeview_administrator;


	@FXML
	TextArea definition;
	@FXML
	TextArea precondition;
	@FXML
	TextArea postcondition;
	@FXML
	TextArea invariants;
	
	@FXML
	TitledPane precondition_pane;
	@FXML
	TitledPane postcondition_pane;
	
	//chosen operation textfields
	List<TextField> choosenOperation;
	String clickedOp;
		
	@FXML
	TableView<ClassInfo> class_statisic;
	@FXML
	TableView<AssociationInfo> association_statisic;
	
	Map<String, ObservableList<AssociationInfo>> allassociationData;
	ObservableList<ClassInfo> classInfodata;
	
	GymSystemSystem gymsystemsystem_service;
	ThirdPartyServices thirdpartyservices_service;
	Cancel_book_CourseService cancel_book_courseservice_service;
	Book_CourseService book_courseservice_service;
	LoginService loginservice_service;
	Register_memberService register_memberservice_service;
	Register_trainerService register_trainerservice_service;
	Create_CourseService create_courseservice_service;
	Update_scheduleService update_scheduleservice_service;
	Finish_CourseService finish_courseservice_service;
	
	ClassInfo course;
	ClassInfo member;
	ClassInfo trainer;
	ClassInfo frontdesk;
	ClassInfo courseroom;
	ClassInfo coursenotification;
	ClassInfo courserecord;
	ClassInfo coursepayment;
		
	@FXML
	TitledPane object_statics;
	Map<String, TableView> allObjectTables;
	
	@FXML
	TitledPane operation_paras;
	
	@FXML
	TitledPane operation_return_pane;
	
	@FXML 
	TitledPane all_invariant_pane;
	
	@FXML
	TitledPane invariants_panes;
	
	Map<String, GridPane> operationPanels;
	Map<String, VBox> opInvariantPanel;
	
	//all textfiled or eumntity
	TextField listAllCourseBooked_member_id_t;
	TextField confirmCancelBook_course_id_t;
	TextField confirmCancelBook_member_id_t;
	TextField requestRefund_course_id_t;
	TextField requestRefund_member_id_t;
	TextField listAllCourseAvailable_member_id_t;
	TextField chooseOneBooking_member_id_t;
	TextField chooseOneBooking_course_id_t;
	TextField payFee_member_id_t;
	TextField payFee_course_id_t;
	TextField payFee_datetime_t;
	TextField login_member_phone_t;
	TextField login_member_password_t;
	TextField login_trainer_phone_t;
	TextField login_trainer_password_t;
	TextField login_frontdesk_phone_t;
	TextField login_frontdesk_password_t;
	TextField input_member_info_id_t;
	TextField input_member_info_name_t;
	TextField input_member_info_age_t;
	TextField input_member_info_phone_t;
	TextField input_member_info_description_t;
	TextField input_member_info_datetime_t;
	TextField input_trainer_info_id_t;
	TextField input_trainer_info_name_t;
	TextField input_trainer_info_age_t;
	TextField input_trainer_info_phone_t;
	TextField input_trainer_info_description_t;
	TextField input_trainer_info_datetime_t;
	TextField requestCreateCourse_trainer_id_t;
	TextField createRoom_room_id_t;
	TextField createRoom_location_t;
	TextField createRoom_capacity_t;
	TextField confirmCourseInfo_room_id_t;
	TextField confirmCourseInfo_course_id_t;
	TextField confirmCourseInfo_register_time_t;
	TextField confirmCourseInfo_register_end_time_t;
	TextField confirmCourseInfo_begin_time_t;
	TextField confirmCourseInfo_end_time_t;
	TextField confirmCourseInfo_course_name_t;
	TextField confirmCourseInfo_description_t;
	TextField confirmCourseInfo_trainer_id_t;
	TextField confirmCourseInfo_cost_t;
	TextField listCourseByTrainer_trainer_id_t;
	TextField chooseSpecificCourse_trainer_id_t;
	TextField chooseSpecificCourse_course_id_t;
	TextField confirmUpdateInfo_course_id_t;
	TextField confirmUpdateInfo_course_name_t;
	TextField confirmUpdateInfo_begin_time_t;
	TextField confirmUpdateInfo_end_time_t;
	TextField confirmUpdateInfo_description_t;
	TextField confirmUpdateInfo_trainer_id_t;
	TextField listAllCourseByTrainer_trainer_id_t;
	TextField confirmCourseFinish_trainer_id_t;
	TextField confirmCourseFinish_course_id_t;
	TextField requestBonus_trainer_id_t;
	TextField requestBonus_course_id_t;
	
	HashMap<String, String> definitions_map;
	HashMap<String, String> preconditions_map;
	HashMap<String, String> postconditions_map;
	HashMap<String, String> invariants_map;
	LinkedHashMap<String, String> service_invariants_map;
	LinkedHashMap<String, String> entity_invariants_map;
	LinkedHashMap<String, Label> service_invariants_label_map;
	LinkedHashMap<String, Label> entity_invariants_label_map;
	LinkedHashMap<String, Label> op_entity_invariants_label_map;
	LinkedHashMap<String, Label> op_service_invariants_label_map;
	

	
}
