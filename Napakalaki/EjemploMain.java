
package Napakalaki;

import GUI.NapakalakyView;
import GUI.PlayerNamesCapture;
import java.util.ArrayList;

public class EjemploMain {

    public static void main(String[] args) {
      Napakalaki napakalakiModel = Napakalaki.getInstance();      
      ArrayList<String> names = new ArrayList<String>();
      NapakalakyView napakalakiView = new NapakalakyView();
      Dice.createInstance(napakalakiView);
      PlayerNamesCapture namesCapture = new PlayerNamesCapture(napakalakiView, true);
      names = namesCapture.getNames();
      napakalakiModel.initGame(names);
      
      napakalakiView.setNapakalaki(napakalakiModel);
       
      napakalakiView.showView();
              
    }
}
