package hr.samara.config;

import org.apache.cxf.interceptor.Fault;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@Component
@EnableAspectJAutoProxy
@Aspect
public class ExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void afterThrowing(ConstraintViolationException ex) throws RuntimeException {
        ConstraintViolation<?> violation = ex.getConstraintViolations().iterator().next();
        logger.info("catched " + violation);
        throw new RuntimeException(violation.getMessage());
    }

    @Pointcut("execution(* hr.samara.web..*(..))")
    public void allMethods() {
    }


    @AfterThrowing(pointcut = "allMethods()", throwing = "exc")
    public void afterThrowingAdviceForAllMethods(JoinPoint jp, Exception exc) throws Throwable {
        logger.info("EXCEPTION class=" + exc.getClass().getSimpleName() + ", message=" + exc.getMessage());
        logger.debug("EXCEPTION ", exc);

        if (exc instanceof ConstraintViolationException) {
            ConstraintViolation<?> violation = ((ConstraintViolationException) exc).getConstraintViolations().iterator().next();
            logger.info("VIOLATION " + violation);
            throw new Fault(new RuntimeException(violation.getMessage()), Fault.FAULT_CODE_CLIENT);
        } else if (exc instanceof EmptyResultDataAccessException) {
            throw new Fault(new RuntimeException("Requested object can not be found in system"), Fault.FAULT_CODE_SERVER);
        }
        // TODO finish
    }
}
