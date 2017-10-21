/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logparser;

/**
 *
 * @author rakib
 */
public class LogParserRunner {

    private static final String dirName = "/var/tmp/parseLog";

    public static void main(String[] args) {
        LogParserManager parseManager = new LogParserManager();
        parseManager.scanDirectory(dirName);
    }

}
