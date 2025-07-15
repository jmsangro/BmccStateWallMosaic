module BmccStateWallMosaic {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	
	opens mosaic to javafx.graphics, javafx.fxml;
}
