package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class PermutationApplication {

	public static void main(String[] args) {
		SpringApplication.run(PermutationApplication.class, args);
	}
}

@RestController
@RequestMapping("/word")
class ReservationApiGatewayRestController {

	@RequestMapping(method = RequestMethod.GET)
	public Set<String> write(@RequestParam String str) {
		return permutationFinder(str);
	}

	public Set<String> permutationFinder(String str) {
		Set<String> perm = new HashSet<>();
		//Handling error scenarios
		if (str == null) {
			return null;
		} else if (str.length() == 0) {
			perm.add("");
			return perm;
		}
		char initial = str.charAt(0); // first character
		String rem = str.substring(1); // Full string without first character
		Set<String> words = permutationFinder(rem);
		words.parallelStream().forEach(strNew -> {
			for (int i = 0;i<=strNew.length();i++){
				perm.add(charInsert(strNew, initial, i));
			}
		});
		System.out.println("Perm size == "+perm.size());
		return perm;
	}

	public String charInsert(String str, char c, int j) {
		String begin = str.substring(0, j);
		String end = str.substring(j);
		String string = begin + c + end;
		return string;
	}
}
