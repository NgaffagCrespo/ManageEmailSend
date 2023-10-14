/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VD.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author p
 */
public class VoteController {
    
     public void passage(ActionEvent e)throws IOException,SQLException{
            new MenuScreen2().start(); 
			}
       
        class MenuScreen2 extends Thread{
		
		@Override
		public void run () {
			
                            try {
                            Thread.sleep(1000);

                            Platform.runLater(new Runnable() {

                                    @Override
                                     public void run () {
                                            Parent root = null;
                                            
                                            try {
                                                     root = FXMLLoader.load(getClass().getResource("/VD/vue/user_vote.fxml"));
                                            }catch(IOException e) {
                                            Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE,null,e);
                                        }

                                            Scene scene = new Scene(root);
                                            Stage primaryStage = new Stage();
                                            primaryStage.setScene(scene);
                                            primaryStage.show();
                                           
                                            
                                     
                                            }
                                            
                                                    });

                            } catch (InterruptedException e) {
                                    // TODO Auto-generated catch block
                                    Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE,null,e);

              }

       }
        }
}
