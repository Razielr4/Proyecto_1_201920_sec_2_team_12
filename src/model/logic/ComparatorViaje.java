package model.logic;

import java.util.Comparator;

public class ComparatorViaje implements Comparator<Viaje>{

	public int compare(Viaje v1, Viaje v2) {
		if(v1 == null) {
			return -1;
		}else if(v2 == null) {
			return 1;
		}
		double num = v1.getMean()-v2.getMean();
		if(num>0 ) {
			return 1;
		}else if(num<0) {
			return -1;
		}
		return 0;
	}

}
