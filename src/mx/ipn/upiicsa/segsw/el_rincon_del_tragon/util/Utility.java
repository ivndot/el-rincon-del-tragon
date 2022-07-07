package mx.ipn.upiicsa.segsw.el_rincon_del_tragon.util;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.Random;

import mx.ipn.upiicsa.segsw.el_rincon_del_tragon.valueobject.RatingRecipeValueObject;

/**
 * 
 * @author Guillermo E. Martinez Barriga
 *
 */

public class Utility 
{
	/* ********************************************************************************* *
	 *                                                                                   *
	 * ********************************************************************************* */
	public static int getRandom(int max)
	{
		return (int) (Math.random()*max);
	}

	/* ********************************************************************************* *
	 *                                                                                   *
	 * ********************************************************************************* */
	public static void closeWriter(Writer writer)
	{
		if(writer != null)
		{
			try
			{
				writer.close();
			}
			catch(IOException ex){}
		}
	}
	/* ********************************************************************************* *
	 *                                                                                   *
	 * ********************************************************************************* */
	public static void flushWriter(Writer writer)
	{
		if(writer != null)
		{
			try
			{
				writer.flush();
			}
			catch(IOException ex){}
		}
	}
	/* ********************************************************************************* *
	 *                                                                                   *
	 * ********************************************************************************* */
	public static String getActivationKey()
	{
		Random random = new Random(new Date().getTime());
		long randomValue = random.nextLong();
		Base64.Encoder encoder = Base64.getEncoder();
		
		return encoder.encodeToString(("" + randomValue).getBytes());
	}
	/* ********************************************************************************* *
	 *                                                                                   *
	 * ********************************************************************************* */
	public static boolean containsAnEmptyValue(String... values)
	{
		for(String value:values)
		{
			if(value == null || value.trim().isEmpty())
			{
				return true;
			}
		}
		return false;
		
	}
	
	/* ********************************************************************************* *
	 *                                                                                   *
	 * ********************************************************************************* */
	public static int calculateAverageRating(ArrayList<RatingRecipeValueObject> ratingList) {
		float average = 0;
		
		for(RatingRecipeValueObject ratingRecipe: ratingList) {
			// se itera por cada objeto de la receta
			// se obtiene su rating y se acumula
			average += ratingRecipe.getRating();
		}
		
		// se saca el promedio y se redondea
		average = average/ratingList.size();
		
		return Math.round(average);
	}
	
	/*
	public static void main(String[] args)
	{
		System.out.println(containsAnEmptyValue("A ", "", "B"));
		
	}
	*/
	/* ********************************************************************************* *
	 *                                                                                   *
	 * ********************************************************************************* */
}
