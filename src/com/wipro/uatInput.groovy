package com.wipro

public class uatInput {

  def buildIsUatApproved() {
    def file = new File("/home/himanshu_kool_hk/test-lib.txt")
    
    if (file.exists()){
      return true;
    }
    else {
      println "Approval file does not exist."
    }
    
    return false;
  }

}
