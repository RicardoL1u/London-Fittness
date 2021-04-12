cd ./src
javac -cp "../lib/javafx-sdk-16/lib/*" -d "../out/production/Combine_All" ./Boundary/*.java

cd ../out/production/Combine_All
java --module-path "../../../lib/javafx-sdk-16/lib" --add-modules "javafx.controls,javafx.fxml,javafx.graphics,javafx.media" Boundary.BoundaryMain