package application;

//them mau khi insert
//delete co visual--> cham cham bay bay

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
@SuppressWarnings("restriction")
public class ArrayListController implements Initializable{
	@FXML
	private Button btnInsert;
	@FXML
    private Button btnDelete;
    @FXML
    private Button btnReset;
    @FXML
    private Button btnDeleteByIndex;
    @FXML
    private TextField txtInsert;
    @FXML
    private TextField txtDelete;
    @FXML
    private TextField txtDeleteByIndex;
	@FXML
    private GridPane gridBoxBall;
	@FXML
    private GridPane gridBoxNum;
    @FXML
    private Label description;   
	
    private int curColumn = 0; 
    private int count = 0;
    
    private int numInsert;
    private int numDelete;
    private int numDeleteIdx;
    private ArrayList<Integer> listNum = new ArrayList<Integer>();
    
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		txtInsert.setTextFormatter(new TextFormatter<Object>(new UnaryOperator<TextFormatter.Change>() {
			@Override
			public TextFormatter.Change apply(TextFormatter.Change change) {
				//cant add character
				change.setText(change.getText().replaceAll("[^0-9]", ""));
				return change;
			}
		}));
		
		txtDelete.setTextFormatter(new TextFormatter<Object>(new UnaryOperator<TextFormatter.Change>() {
			@Override
			public TextFormatter.Change apply(TextFormatter.Change change) {
				change.setText(change.getText().replaceAll("[^0-9]", ""));		
				return change;
			}
		}));
	}
	
	@FXML
	public void actionInsert(ActionEvent event){
		try {
			if(curColumn >=8) {
				description.setText("ArrayList full already, you can not insert more!");
				txtInsert.setText("");
			}
			else {
				System.out.println("Button Insert Clicked!");
				numInsert = Integer.parseInt(txtInsert.getText());
				addCircle(curColumn, 0);
				addLabel(curColumn, 0, numInsert);
				listNum.add(numInsert);
				txtInsert.setText("");
				description.setText("Insert successfully!");
				curColumn ++;
				count++;
			}
		} catch (Exception e) {
			System.err.println("co loi xay ra: " + e.getMessage());
			e.printStackTrace();
		}
	}
	@FXML 
	public void actionDelete(ActionEvent event) {
		try {
			if(curColumn == 0) {
				description.setText("Nothing to delete.");
				txtDelete.setText("");
			}
			else {
				numDelete = Integer.parseInt(txtDelete.getText());
				Integer objInteger = new Integer(numDelete);
				if(listNum.contains(numDelete)) {
					description.setText("Delete "+ numDelete +" successfully!");
				} else if(!listNum.contains(numDelete)){
					description.setText("No " + numDelete +" in list." );
				}
				listNum.removeAll(Collections.singleton(objInteger));
				gridBoxBall.getChildren().clear();
				gridBoxNum.getChildren().clear();
				addListCircle(0, 0, listNum);
				addListLabel(0, 0, listNum);
				txtDelete.setText("");
				
				int reduce = curColumn - listNum.size();
				curColumn =  curColumn - reduce;
				count = count - reduce;
			}
		} catch (Exception e) {
			System.err.println("co loi xay ra: " + e.getMessage());
			e.printStackTrace();			
		}
	}
	@FXML
	public void actionDeleteByIndex(ActionEvent event) {
		try {
			if(curColumn == 0) {
				description.setText("Nothing to delete.");
				txtDeleteByIndex.setText("");
			}
			else {
				numDeleteIdx = Integer.parseInt(txtDeleteByIndex.getText());
				if(numDeleteIdx < curColumn) {
					description.setText("Delete position " +numDeleteIdx+ " successfully!");
				} else if(numDeleteIdx >= curColumn){
					description.setText("No number at position " + numDeleteIdx);
				}
				listNum.remove(numDeleteIdx);
				gridBoxBall.getChildren().clear();
				gridBoxNum.getChildren().clear();
				addListCircle(0, 0, listNum);
				addListLabel(0, 0, listNum);
				txtDeleteByIndex.setText("");
				
				curColumn --;
				count --;
			}
		} catch (Exception e) {
			System.err.println("co loi xay ra: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	@FXML 
	public void actionReset(ActionEvent event) {
		listNum.clear();
		gridBoxBall.getChildren().clear();
		gridBoxNum.getChildren().clear();
		curColumn = 0;
		count = 0;
		description.setText("Reset successfully!");
	}

	public void addCircle (int col, int row) {
		Circle circle = new Circle(20);
		circle.setFill(Color.BLUE);
		gridBoxBall.add(circle, col, row);
	}
	
	public void addLabel (int col, int row, int value) {
		Label label = new Label(value + "");
		label.setPrefSize(40, 40);
		label.setAlignment(Pos.CENTER);
        gridBoxNum.add(label, col, row);
	}
	
	public void addListCircle(int col, int row, ArrayList<Integer> numList) {
		for(int i = 0; i < numList.size(); i++) {
			addCircle(i, 0);
		}
	}
	
	public void addListLabel (int col, int row, ArrayList<Integer> numList) {
		for(int i = 0; i < numList.size(); i++) {
			addLabel(i, 0, numList.get(i));
		}
	}
}