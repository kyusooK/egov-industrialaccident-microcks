package egovframework.com.cmm;

import lombok.extern.slf4j.Slf4j;
import org.egovframe.rte.fdl.cmmn.exception.handler.ExceptionHandler;

@Slf4j
public class EgovComOthersExcepHndlr implements ExceptionHandler {

    public void occur(Exception exception, String packageName) {
        //log.debug(" EgovServiceExceptionHandler run...............");
        log.error(packageName, exception);
    }
}
