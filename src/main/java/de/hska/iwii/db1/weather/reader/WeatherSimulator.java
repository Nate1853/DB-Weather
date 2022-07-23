package de.hska.iwii.db1.weather.reader;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import de.hska.iwii.db1.weather.model.Location;
import de.hska.iwii.db1.weather.model.LocationType;
import de.hska.iwii.db1.weather.model.Weather;
import de.hska.iwii.db1.weather.model.WeatherContainer;

/**
 * Erzeugt simulierte Wetterdaten.
 */
public class WeatherSimulator {
	
	// Simulierte Orte
	private static final Map<String, Location> simulatedLocations = new HashMap<>();

	// Simulierte Daten
	private static final Map<Long, WeatherContainer> simulatedWeather = new HashMap<>();
	
	static {
		simulatedLocations.put("karlsruhe", new Location("Karlsruhe", LocationType.CITY.name(), 62518, "49.0093,8.4055"));
		simulatedLocations.put("hamburg", new Location("Hamburg", LocationType.CITY.name(), 20833623, "53.5503410,10.0006540"));
		
		simulatedWeather.put(62518L, new WeatherContainer(new Weather[] {
						new Weather(1, "sunny", new Date(), 12, 31, 10, 33),
						new Weather(2, "sunny", toDate(LocalDate.now().plusDays(1)), 18, 37, 11, 30),
						new Weather(3, "sunny", toDate(LocalDate.now().plusDays(2)), 20, 38, 12, 27)
					}));

		simulatedWeather.put(20833623L, new WeatherContainer(new Weather[] {
						new Weather(4, "partly cloudy", new Date(), 18, 24, 12, 41),
						new Weather(5, "mostly sunny", toDate(LocalDate.now().plusDays(1)), 13, 26, 11, 54),
						new Weather(6, "rain", toDate(LocalDate.now().plusDays(2)), 11, 19, 9, 66)
			}));
}
	
	/**
	 * Erzeugt aus einem LocalDate-Object ein Date-Objekt.
	 * @param date Datum im LocalDate-Format.
	 * @return Datum im Date-Format- 
	 */
	private static Date toDate(LocalDate date) {
		return Date.from(((LocalDate) date).atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
	
	/**
	 * Erzeugt die simulierten Daten.
	 */
	public WeatherSimulator() {
	}

	/**
	 * Sucht eine Ortsangabe anhand ihres Namens.
	 * @param name Name der Ortsangabe wie z.B. "Stuttgart"
	 * @return Alle Ortsangaben zu diesem Namen. Gibt es keinen
	 * 			Ort mit dem Namen oder tritt ein Fehler auf, dann
	 * 			ist das Array leer.
	 */
	public Location[] findLocation(String name) {
		Location location = simulatedLocations.get(name.toLowerCase());
		return location != null ? new Location[] {location} : new Location[ 0 ];
	}

	/**
	 * Liest das aktuelle Wetter an einem Ort aus.
	 * @param locationId ID des Ortes.
	 * @return Aktuelles Wetten an dem Ort oder <code>null</code>.
	 * 			Im Fall eines Fehlers wird ebenfalls <code>null</code>
	 * 			zurueckgegeben.
	 */
	public Weather readCurrentWeather(long locationId) {
		WeatherContainer weatherInfo = simulatedWeather.get(locationId);
		return weatherInfo != null ? weatherInfo.getWeather()[0] : null;
	}
}
