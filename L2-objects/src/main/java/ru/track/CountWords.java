package ru.track;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


/**
 @@ -26,9 +28,10 @@
 */
public class CountWords {

    String skipWord;
    String skipWord = "vu53f28MvpQ4PclHvxHZ";

    public CountWords(String skipWord) {
            public CountWords(String skipWord)
                    {
                this.skipWord = skipWord; }

        @@ -39,8 +42,27 @@
 public CountWords(String skipWord) {
      /** @param file - файл с данными
      * @return - целое число - сумма всех чисел из файла
      */
                public long countNumbers(File file) throws Exception {
                        return 0;
                    public long countNumbers(File file) throws Exception
                            {
                                FileReader fileReader = new FileReader (file);
                        BufferedReader bufferedReader = new BufferedReader(fileReader);
                        String line = null;
                        long sum = 0;
                        while (true)
                            {
                                        line = bufferedReader.readLine();
                            if (line == null)
                                {
                                            break;
                            }
                            try {
                                    sum += Integer.parseInt(line);
                                } catch (NumberFormatException e)
                            {
                                    }

                                }
                        return sum;
     }


                @@ -51,8 +73,48 @@ public long countNumbers(File file) throws Exception {
     /* * @param file - файл с данными
      * @return - результирующая строка
                            */

                                public static void main (String[] args) throws Exception{
                                CountWords cw = new CountWords("");
                                File src = new File ("C:\\Users\\haker\\Desktop\\технотрек\\java\\track18-spring\\L2-objects\\words.txt");
                                System.out.println(cw.concatWords(src));
                            }

                    public String concatWords(File file) throws Exception {
                                return null;
                                StringBuilder result = new StringBuilder("");
                                FileReader fileReader = new FileReader (file);
                                BufferedReader bufferedReader = new BufferedReader(fileReader);
                                String line = "";
                                boolean num = false;
                                boolean empty = false;
                                //1
                                        while (true)
                                    {
                                                line = bufferedReader.readLine();
                                    if (line == null)
                                        {
                                                    break;
                                    }
                                    if ((line.equals("")) || (line.equals(skipWord)))
                                        {
                                                    empty = true;
                                    }
                                    try {
                                            Integer.parseInt(line);
                                        } catch (NumberFormatException e)
                                    {
                                                num = true;
                                    }

                                            if ((num != false) &&(empty == false))
                                        {
                                                    result.append(line);
                                        result.append(" ");
                                    }
                                    num = false;
                                    empty = false;
                                }
                                return result.toString();
                    }

                }
