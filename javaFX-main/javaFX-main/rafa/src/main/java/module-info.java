/* doesn't work with source level 1.8:
module com.mycompany.jfx {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.jfx to javafx.fxml;
    exports com.mycompany.jfx;
}
*/
