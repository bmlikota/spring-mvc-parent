package hr.bm.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspect {

	@Pointcut("execution(* *ThymeleafController.thymeLeafExercise(..))")
	public void print() {
	}

	// TODO ovo ne radi
	@Before("execution(* hr.bm.web.controller.ThymeleafController.thymeLeafExercise(..))")
	public void aspectPrint() {
		System.out.println("Ovo je print iz aspecta! :-)");
	}

}
