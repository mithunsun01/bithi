



public class StudentList {

    static BufferedReader bufferedReader;
    static String lines;
    static String [] studentNames;

    // This method will create object of BufferReader and load lines in studentNames String Array. It is used to avoid repeatative calling.
    public static void getReader(){

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("students.txt")));
            lines = bufferedReader.readLine();
            studentNames = lines.split(",");
        }

        catch (FileNotFoundException e) {
            System.out.println(Constants.FileIOExMessage);
        }
        catch (IOException e) {
            System.out.println(Constants.IOExMessage);
        }

    }

    // Main Method
    public static void main(String[] args) {
        getReader();

        // Check for invalid arguments

        // Check if user pass no argument
        if (args.length == 0) {
            System.out.println(Constants.ZeroArgument);
        }

        // Check if user pass more than 1 argument
        else if (args.length > 1) {
            System.out.println(Constants.MultipleArgument);

        }

        // Check if user pass 1 argument but that is incorrect (Ex: java StudentList Akif)

        else if (!args[0].startsWith("@") && !args[0].startsWith("+") && args[0].length() > 1) {
            System.out.println(Constants.WrongArgument);

        }

        //Check if user pass 'a' to see the list of students.

        else if (args[0].equals("a")) {

            System.out.println(Constants.DataLoadingProgress);

            try {

                for (String name : studentNames) {
                    if(!name.startsWith("("))
                        System.out.println(name);

                }

            } catch (Exception e) {
                System.out.println(Constants.DataLoadingFail);
            }

            System.out.println(Constants.DataLoadSucessMsg);
        }

        //Check if user pass 'r' to see a random student.

        else if (args[0].equals("r")) {

            System.out.println(Constants.DataLoadingProgress);
            System.out.println("Random Students...");
            try {


                Random random = new Random();
                int indexOfRandomStudent = random.nextInt(studentNames.length-1);
                if(studentNames[indexOfRandomStudent].startsWith("("))
                    indexOfRandomStudent--;
                System.out.println(studentNames[indexOfRandomStudent]);

            } catch (Exception e) {
                System.out.println(Constants.DataLoadingFail);
            }

            System.out.println(Constants.DataLoadSucessMsg);
        }

        //Check if user pass '+' to add a student name

        else if (args[0].contains("+")) {


            try {


                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("students.txt", true));

                String newStudentName = args[0].substring(1);
                Date todaysDate = new Date();

                String formatedDateString = Constants.formatedDateString;
                DateFormat dateFormat = new SimpleDateFormat(formatedDateString);

                String currentDateTime = dateFormat.format(todaysDate);
                bufferedWriter.write(newStudentName + ",(Added on " + currentDateTime+ "),");
                System.out.println("New Data Added.");
                bufferedWriter.close();

            } catch (Exception e) {
                System.out.println("Could not add new data");
            }



        }

        //Check if user pass '@' to check if a student present in the list

        else if (args[0].contains("@")) {

            System.out.println(Constants.DataLoadingProgress);

            try {
                //Take the substring with @ symbol. (Ex : Making from @Akif to Akif)
                String studentName = args[0].substring(1);

                for (int idx = 0; idx < studentNames.length; idx++) {

                    if (studentNames[idx].startsWith(studentName)) {
                        System.out.println(Constants.FoundYesMessage);
                        System.out.println(Constants.DataLoadSucessMsg);
                        return;
                    }

                }

                System.out.println(Constants.FoundNoMessage);


            } catch (Exception e) {
                System.out.println(Constants.DataLoadingFail);
            }

        }

        // Count the number of words and characters in the file.

        else if (args[0].contains("c")) {

            System.out.println(Constants.DataLoadingProgress);

            try {
                int totalChars = 0;
                int totalWords = 0;

                for (String student : studentNames) {
                    if (!student.startsWith("(")) {
                        // Check if a name contains 2-3 words splited by space like "Mehedi Hasan Tarik".
                        for (String word : student.split(" ")){
                            totalWords++;
                            totalChars+=word.length();
                        }
                    }
                }


                System.out.println("Word(s) found " + totalWords);
                System.out.println("Characters(s) found " + totalChars);
            }
            catch (Exception e) {
                System.out.println(Constants.DataLoadingFail);
            }
            System.out.println(Constants.DataLoadSucessMsg);
        }


         else {
            System.out.println(Constants.WrongArgument);
        }
    }
}
