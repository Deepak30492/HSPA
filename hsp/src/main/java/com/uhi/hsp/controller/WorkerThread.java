package com.uhi.hsp.controller;

import org.springframework.web.client.RestTemplate;

class WorkerThread implements Runnable {
String resp="";
public WorkerThread(String resp) {
	this.resp=resp;
}
  @Override
  public void run() {
    // for (int i = 0; i < 5; i++) {
         try {
              Thread.sleep(10000);
             // Thread.sleep(1000, 500);
              System.out.println("[" + Thread.currentThread().getName() + "] Message ");
              RestTemplate template = new RestTemplate();
              System.out.println("posting response to gateway"+resp);
              template.postForObject("http://localhost:3131/gateway/on_search", resp, String.class);
         } catch (final InterruptedException e) {
              e.printStackTrace();
         }
     // }
   }
}