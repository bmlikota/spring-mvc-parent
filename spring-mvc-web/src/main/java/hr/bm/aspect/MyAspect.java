package hr.bm.aspect;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {

	@Pointcut("execution(* *ThymeleafController.thymeLeafExercise(..))")
	public void print() {
	}

	// TODO ovo ne radi
	@Around("execution(* hr.bm.web.controller.ThymeleafController..*(..))")
	public void aspectPrint() {
		System.out.println("Ovo je print iz aspecta! :-)");
	}

}
