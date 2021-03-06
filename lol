<?xml version="1.0" encoding="UTF-8"?>

<?import javafx.geometry.Insets?>
<?import javafx.scene.Cursor?>
<?import javafx.scene.control.Button?>
<?import javafx.scene.control.ButtonBar?>
<?import javafx.scene.control.Label?>
<?import javafx.scene.control.TableColumn?>
<?import javafx.scene.control.TableView?>
<?import javafx.scene.control.TextField?>
<?import javafx.scene.layout.BorderPane?>
<?import javafx.scene.layout.HBox?>
<?import javafx.scene.text.Font?>

<BorderPane fx:id="borderpane" maxHeight="-Infinity" maxWidth="-Infinity" minHeight="-Infinity" minWidth="-Infinity" prefHeight="600.0" prefWidth="600.0" xmlns="http://javafx.com/javafx/8.0.171" xmlns:fx="http://javafx.com/fxml/1" fx:controller="application.huuhedController">
   <center>
      <HBox prefHeight="69.0" prefWidth="327.0" BorderPane.alignment="CENTER">
         <children>
            <HBox prefHeight="66.0" prefWidth="307.0">
               <children>
                  <TextField fx:id="ner" prefHeight="27.0" prefWidth="151.0" promptText="Нэр">
                     <HBox.margin>
                        <Insets left="10.0" top="20.0" />
                     </HBox.margin>
                  </TextField>
                  <Button fx:id="btnHaih" mnemonicParsing="false" onAction="#btnHaihAction" prefHeight="27.0" prefWidth="76.0" text="Хайх">
                     <HBox.margin>
                        <Insets left="10.0" top="20.0" />
                     </HBox.margin>
                     <cursor>
                        <Cursor fx:constant="HAND" />
                     </cursor>
                  </Button>
                  <Label fx:id="seeAll" onMouseClicked="#bugdiigHarah" prefHeight="47.0" prefWidth="46.0" text="Бүгдийг харах" textAlignment="CENTER" textFill="#2600ff" wrapText="true">
                     <HBox.margin>
                        <Insets left="10.0" top="10.0" />
                     </HBox.margin>
                     <font>
                        <Font size="11.0" />
                     </font>
                     <cursor>
                        <Cursor fx:constant="HAND" />
                     </cursor>
                  </Label>
               </children>
            </HBox>
            <HBox prefHeight="69.0" prefWidth="311.0">
               <children>
                  <ButtonBar prefHeight="66.0" prefWidth="297.0">
                    <buttons>
                      <Button fx:id="btnZasah" minWidth="50.0" mnemonicParsing="false" onAction="#btnZasahAction" prefHeight="27.0" prefWidth="107.0" text="Засварлах">
                           <cursor>
                              <Cursor fx:constant="HAND" />
                           </cursor></Button>
                        <Button fx:id="btnNemeh" mnemonicParsing="false" onAction="#btnNemehAction" prefHeight="27.0" prefWidth="77.0" text=" Нэмэх">
                           <cursor>
                              <Cursor fx:constant="HAND" />
                           </cursor></Button>
                        <Button fx:id="btnUstgah" mnemonicParsing="false" onAction="#btnUstgahAction" text="Устгах">
                           <cursor>
                              <Cursor fx:constant="HAND" />
                           </cursor></Button>
                    </buttons>
                     <HBox.margin>
                        <Insets />
                     </HBox.margin>
                     <padding>
                        <Insets right="3.0" />
                     </padding>
                  </ButtonBar>
               </children>
               <HBox.margin>
                  <Insets />
               </HBox.margin>
            </HBox>
         </children>
         <BorderPane.margin>
            <Insets />
         </BorderPane.margin>
      </HBox>
   </center>
   <bottom>
      <TableView fx:id="tableview" prefHeight="534.0" prefWidth="600.0" BorderPane.alignment="CENTER">
        <columns>
            <TableColumn fx:id="colHDugaar" prefWidth="27.0" resizable="false" text="#" />
          <TableColumn fx:id="colHOvog" prefWidth="175.0" text="Овог" />
          <TableColumn fx:id="colHNer" prefWidth="175.0" text="Нэр" />
            <TableColumn fx:id="colHHuis" prefWidth="50.0" text="Хүйс" />
            <TableColumn fx:id="colHReg" maxWidth="10000.0" prefWidth="175.0" text="Регистерийн дугаар" />
        </columns>
      </TableView>
   </bottom>
</BorderPane>