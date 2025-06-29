#!/bin/sh
sleep 5
export DISPLAY=:0
# Replace each java command with your corresponding command (used Mosaics.sh to find)
java --module-path /home/administrator/java-lib:/usr/share/openjfx/lib --add-modules javafx.controls,javafx.fxml -DimagesDir=/home/administrator/Documents/BmccStateWallMosaic/images/Montana -cp /home/administrator/Documents/BmccStateWallMosaic/bin mosaic.Main&
PID1=$!
echo $PID1
sleep 5

# Get window IDs (searching by PID is more reliable, can use a name search)
WIN1=$(xdotool search --onlyvisible --pid $PID1 | head -n 1)
echo win1
echo $WIN1
# Move and fullscreen the first window (Monitor 1)
xdotool windowactivate $WIN1
wmctrl -i -r $WIN1 -e 0,0,0,-1,-1   # Move to screen 1 (usually starts at 0,0)
xdotool windowactivate $WIN1 key F11  # Fullscreen

sleep 5

java --module-path /home/administrator/java-lib:/usr/share/openjfx/lib --add-modules javafx.controls,javafx.fxml -DimagesDir=/home/administrator/Documents/BmccStateWallMosaic/images/Utah -cp /home/administrator/Documents/BmccStateWallMosaic/bin mosaic.Main&&
PID2=$!
echo $PID2
# Wait for windows to open
sleep 5

WIN2=$(xdotool search --onlyvisible --pid $PID2 | head -n 1)
echo win2
echo $WIN2


# Move and fullscreen the second window (Monitor 2)
# Assuming second monitor starts at X=1050 (adjust as needed)
xdotool windowactivate $WIN2
wmctrl -i -r $WIN2 -e 1080,0,0,-1,-1
xdotool windowactivate $WIN2 key F11
