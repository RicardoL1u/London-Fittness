cd ./src
javac -cp "../lib/json/*" -d "../out/production/Combine_All" ./Database/*.java

cd Controller
javac -cp "../../lib/json/*:../" -d "../../out/production/Combine_All" ./*.java

cd ../../out/production/Combine_All
java -cp ".:../../../lib/json/*" Controller.ControlServer
