/**
 * 
 */
/**
 * 
 */
module Library {
	requires javafx.controls;
    requires javafx.fxml;
    
    opens Library to javafx.fxml;
    exports Library;
}