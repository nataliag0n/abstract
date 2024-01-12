package com.demo.ProyectoDemo.restController;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demo.ProyectoDemo.entity.DemoEntity;
import com.demo.ProyectoDemo.exception.DemoNotFoundException;
import com.demo.ProyectoDemo.repository.DemoRepository;
import com.demo.ProyectoDemo.util.Constant;
import com.demo.ProyectoDemo.util.Util;

@RestController
@RequestMapping(value = "/demo")
public class DemoController {
	
	private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

	@Autowired
	DemoRepository demoRepository;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<DemoEntity>> getAll(){
		logger.info(Constant.GET_ALL);
		ResponseEntity<List<DemoEntity>> responseEntity = null;
		try {
			responseEntity = ResponseEntity.status(HttpStatus.OK).body(demoRepository.findAll());
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		return responseEntity;
	}
	
	@GetMapping("/entity/id/{id}")
	ResponseEntity<Optional<DemoEntity>> getById(@PathVariable("id") Integer id) throws Exception {
		logger.info(Constant.GET_BY_ID, id);
		ResponseEntity<Optional<DemoEntity>> responseEntity = null;
		try {
			responseEntity = ResponseEntity.status(HttpStatus.OK).body(demoRepository.findById(id));
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new Exception(new DemoNotFoundException(id));
		}
		return responseEntity;
	}
	
	@ResponseStatus(value = HttpStatus.OK)
    @PostMapping(value="/entity", consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<DemoEntity> create(@RequestBody DemoEntity demoEntity) {
		logger.info(Constant.CREATE, demoEntity.toString());
		ResponseEntity<DemoEntity> responseEntity = null;
		try {
			responseEntity = ResponseEntity.status(HttpStatus.OK).body(demoRepository.save(demoEntity));
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		return responseEntity;
	}
	
	@ResponseStatus(value = HttpStatus.OK)
    @PutMapping(value="/entity", consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<DemoEntity> update(@RequestBody DemoEntity demoEntity) {
		logger.info(Constant.UPDATE, demoEntity.toString());
		ResponseEntity<DemoEntity> responseEntity = null;
		DemoEntity e = demoEntity;
		try {
			Optional<DemoEntity> entity = demoRepository.findById(demoEntity.getId());
			if (entity.isPresent()) {
				e = entity.get();
				e.setRef(demoEntity.getRef());
				e.setDistance(demoEntity.getDistance());
				e.setVelocity(demoEntity.getVelocity());
				e.setClockwise(demoEntity.getClockwise());
			}
			responseEntity = ResponseEntity.status(HttpStatus.OK).body(demoRepository.save(demoEntity));
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		return responseEntity;
	}
	
	@DeleteMapping("/delete/entity/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	ResponseEntity<DemoEntity> delete(@PathVariable("id") Integer id) {
		logger.info(Constant.UPDATE, id);
		try {
			demoRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body(null);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	@RequestMapping(value="/drought", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Integer> howManyPeriodsOfDrought(){
		logger.info(Constant.DROUGHT);
		List<DemoEntity> demos = demoRepository.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(Util.howManyPeriodsOfDrought(demos));
	}
	
	@RequestMapping(value="/rain", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Integer> howManyPeriodsOfRain(){
		logger.info(Constant.RAIN);
		List<DemoEntity> demos = demoRepository.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(Util.howManyPeriodsOfRain(demos));
	}
	
	@RequestMapping(value="/pressure", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Integer> howManyPeriodsOfPressure(){
		logger.info(Constant.PRESSURE);
		List<DemoEntity> demos = demoRepository.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(Util.howManyPeriodsOfPressure(demos));
	}
	
	@RequestMapping(value="/test", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	DemoEntity test(){
		logger.info(Constant.TEST);
		DemoEntity demoEntity = new DemoEntity();
		return demoEntity;
	}
	
}