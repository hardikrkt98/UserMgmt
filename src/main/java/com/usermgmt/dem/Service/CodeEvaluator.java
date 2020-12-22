package com.usermgmt.dem.Service;

import com.usermgmt.dem.domain.Code;
import com.usermgmt.dem.domain.TestCase;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;


@Service
public class CodeEvaluator {
    public  void evaluateCode (List<TestCase> testCases, Code code) throws FileNotFoundException {
        PrintWriter out = new PrintWriter("temp.cpp");
        PrintWriter ouerasdt = new PrintWriter("error.txt");
        PrintWriter ouera34sdt = new PrintWriter("output.txt");
        out.println(code.getCode());
        out.close();
        File commands = new File("commands.txt");
        File output = new File("output.txt");

//        String [] listDirectoryCommand = {"g++" ,"temp.cpp"};


        ProcessBuilder compile = new ProcessBuilder("g++", "temp.cpp");




        ProcessBuilder execute = new ProcessBuilder("./a.out");

//        pB.directory(new File(System.getProperty("user.home")));



        try {
           Process  p =  compile.start();
//            pB.redirectInput(commands);
            int exitCode = p.waitFor();
            if(exitCode==0) {
                execute.redirectOutput(output);
                execute.redirectError(new File("error.txt"));
                execute.start();
            }
//            BufferedReader readOutput =
//                    new BufferedReader (new InputStreamReader(p.getInputStream()));
//
//            String outputCommandLine;
//
//            while ((outputCommandLine = readOutput.readLine()) != null) {
//                System.out.println(outputCommandLine);
//            }
//
//            int exitCode = p.waitFor();
//
//            System.out.println ("\nExited with error code : " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}