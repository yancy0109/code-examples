package com.yancy.test;

import com.yancy.service.engine.impl.ReceiveEngineImpl;
import com.yancy.service.mode.BehaviorMatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * @author yancy0109
 * @date: 2023/8/18
 */
public class TalkEngineTest {

    private Logger logger = LoggerFactory.getLogger(TalkEngineTest.class);

    private ReceiveEngineImpl receiveEngine;

    public void init() {
        receiveEngine = new ReceiveEngineImpl();
        receiveEngine.init();
    }


    public void talkTest() {
        init();
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            input = scanner.nextLine();
            if ("exit".equals(input)) {
                return;
            }
            String text = receiveEngine.process(
                    new BehaviorMatter("1", "text", input, null)
            );
            logger.info("ReceiveEngine: " + text);
        }
    }


    public static void main(String[] args) {
        new TalkEngineTest().talkTest();
    }

}
