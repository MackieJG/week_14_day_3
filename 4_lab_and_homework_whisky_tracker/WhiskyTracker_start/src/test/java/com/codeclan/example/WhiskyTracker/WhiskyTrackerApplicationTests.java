package com.codeclan.example.WhiskyTracker;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@ActiveProfiles("test") //Indicates it's a test profile so will not run DataLoader
@SpringBootTest
public class WhiskyTrackerApplicationTests {
	@Autowired
	WhiskyRepository whiskyRepository;

	@Autowired
	DistilleryRepository distilleryRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void canGetWhiskiesByYear(){
		List<Whisky> foundWhiskies = whiskyRepository.findByYear(2010);
		assertEquals(1, foundWhiskies.size());
	}

	@Test
	public void canGetDistilleriesByRegion(){
		List<Distillery> foundDistillery = distilleryRepository.findByRegion("Highland");
		assertEquals(12, foundDistillery.size());
	}

	@Test
	public void canGetWhiskiesByAgeFromDistillery(){
		List<Whisky> foundWhiskies = whiskyRepository.findByDistilleryAndAge(distilleryRepository.getById(5L), 12);
		assertEquals(1, foundWhiskies.size());
	}

	@Test
	public void canGetWhiskiesByFromAParticularRegion(){
		List<Whisky> foundWhiskies = whiskyRepository.findByDistilleryRegion("Lowland");
		assertEquals(32, foundWhiskies.size());
	}

	@Test
	public void canGetDistilleriesWithWhiskiesOfAge12(){
		List<Distillery> foundDistilleries= distilleryRepository.findByWhiskiesAge(12);
		assertEquals(54, foundDistilleries.size());
	}
}
