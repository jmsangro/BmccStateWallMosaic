#!/bin/sh
sleep 5
java --module-path /home/administrator/java-lib:/usr/share/openjfx/lib --add-modules javafx.controls,javafx.fxml -DimagesDir=/home/administrator/Documents/BmccStateWallMosaic/images/Idaho -DstyleSheet=idaho.css -Dscreen=0 -cp /home/administrator/Documents/BmccStateWallMosaic/bin mosaic.Main

sleep 5

# Replace each java command with your corresponding command (used Mosaics.sh to find)
java --module-path /home/administrator/java-lib:/usr/share/openjfx/lib --add-modules javafx.controls,javafx.fxml -DimagesDir=/home/administrator/Documents/BmccStateWallMosaic/images/Washington -DstyleSheet=washinton.css -Dscreen=1 -cp /home/administrator/Documents/BmccStateWallMosaic/bin mosaic.Main



