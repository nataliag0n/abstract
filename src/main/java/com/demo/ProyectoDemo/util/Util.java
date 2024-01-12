package com.demo.ProyectoDemo.util;

import java.util.List;

import com.demo.ProyectoDemo.entity.DemoEntity;
import com.demo.ProyectoDemo.entity.Point;

public class Util {
	
	static final Point sun = new Point(0,0);
	
	public static Integer howManyPeriodsOfDrought(List<DemoEntity> list) {
		int count=0;
		for (Point point1: list.get(0).getPoints())
			for (Point point2: list.get(1).getPoints())
				for (Point point3: list.get(2).getPoints())
					if (isAPeriodOfDrought(point1, point2, point3))
						count++;
		return count;
	}
	
	public static Integer howManyPeriodsOfRain(List<DemoEntity> list) {
		int count=0;
		for (Point point1: list.get(0).getPoints())
			for (Point point2: list.get(1).getPoints())
				for (Point point3: list.get(2).getPoints())
					if (isAPeriodOfRain(point1, point2, point3))
						count++;
		return count;
	}
	
	public static Integer howManyPeriodsOfPressure(List<DemoEntity> list){
		int count=0;
		for (Point point1: list.get(0).getPoints())
			for (Point point2: list.get(1).getPoints())
				for (Point point3: list.get(2).getPoints())
					if (isAPeriodOfPressure(point1, point2, point3))
						count++;
		return count;
	}
	
	public static boolean isAPeriodOfDrought(Point point, Point point2, Point point3) {
		if (!arePointsAligned(point, point2, point3))
			return false;
		
		if (!areAligned(point, sun))
			return false;
		
		return true;
	}
	
	public static boolean isAPeriodOfRain(Point point1, Point point2, Point point3) {
		return isATriangle(point1, point2, point3);
	}
	
	public static boolean isAPeriodOfPressure(Point point1, Point point2, Point point3) {

		if (!areAligned(point1, point2))
			return false;
		
		if (!areAligned(point2, point3))
			return false;

		return true;
	}
	
	public static boolean arePointsAligned(Point point, Point point2, Point point3) {

		if (!areAligned(point, point2))
			return false;
		
		if (!areAligned(point2, point3))
			return false;
		
		return true;
	}
	
	public static boolean areAligned(Point point, Point point2) {
		return true;
	}
	
	public static boolean isATriangle(Point point, Point point2, Point point3){
		return true;
	}

}
