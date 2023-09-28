/**
 * Main class that has the driver that will run the code
 */
public class GuiDriver {
    /**
     * main driver that has several while loops to show a gui if the boolean in said gui is true
     * the Gui will change the boolean to false if the user completes the actions in the qui
     *
     */
    public static void main(String[] args){

          //For every object creation below except for quiz summary the program creates an object to a class
          //Each of the classes is a different gui. The gui's setVisible operator is set till true in a while loop
          //the while loop will exit when the boolean exit of each class turns to false when the next button is pushed
          //Once it exits the loop the object is set to be invisible and the next object it set to be visible

          FrontPage startPage = new FrontPage();
          while(startPage.exit){
             startPage.setVisible(true);
          }
          startPage.setVisible(false);


          RadioButtonQuestion firstQuestion = new RadioButtonQuestion();
          while(firstQuestion.exit){
              firstQuestion.setVisible(true);
          }
          firstQuestion.setVisible(false);

          CheckboxQuestion secondQuestion =  new CheckboxQuestion();
          while(secondQuestion.exitTwo){
              secondQuestion.setVisible(true);
          }
          secondQuestion.setVisible(false);

        DropdownBoxQuestion thirdQuestion = new DropdownBoxQuestion();
          while(thirdQuestion.exitThree){
              thirdQuestion.setVisible(true);
          }
          thirdQuestion.setVisible(false);

          ListQuestion fourthQuestion = new ListQuestion();
          while(fourthQuestion.exitFour){
              fourthQuestion.setVisible(true);
          }
          fourthQuestion.setVisible(false);

          SubmitQuiz cheatingPolicy = new SubmitQuiz();
          while(cheatingPolicy.cheating){
              cheatingPolicy.setVisible(true);

          }
          cheatingPolicy.setVisible(false);
          //Quiz summary doesn't have its visibility in a while loop, because when QuizSummary ends the whole program ends.
          QuizSummary quizResults = new QuizSummary();
          quizResults.setVisible(true);

    }
}
