module BmccStateWallMosaic {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	
	opens mosaic to javafx.graphics, javafx.fxml;
}
