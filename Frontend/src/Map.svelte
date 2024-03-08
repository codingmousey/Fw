<!-- Scripts -->
<!--
    Source: https://leafletjs.com/examples/quick-start/
-->
<script>
  import { onMount } from "svelte";
  import L from "leaflet";
  import { filteredJobListings } from "./db.js";

  onMount(() => {
    // setting up the map (geocode for Anderlecht EhB)
    const map = L.map("map").setView([50.8390983, 4.3296526], 8);

    const unsubscribe = filteredJobListings.subscribe(
      ($filteredJobListings) => {
        map.eachLayer((layer) => {
          if (layer instanceof L.Circle) {
            map.removeLayer(layer);
          }
        });

        console.log(
          "joblistings data:",
          JSON.stringify($filteredJobListings, null, 2)
        );

        // add tile layer to the map
        L.tileLayer("https://tile.openstreetmap.org/{z}/{x}/{y}.png", {
          maxZoom: 19,
          attribution:
            '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>',
        }).addTo(map);

        // adding markers to the map by getting the lattitude and longitude from an api call to nominatim
        // source: https://nominatim.org/release-docs/develop/api/Search/
        $filteredJobListings.forEach((i) => {
          const address = i.city;
          console.log("city van json obj: " + address);
          fetch(
            `https://nominatim.openstreetmap.org/search?format=json&q=${address},Belgium`,
            {
              headers: new Headers({
                "Access-Control-Allow-Origin": "*",
              }), 
            }
          )
            .then((res) => res.json())
            .then((data) => {
              if (data.length > 0) {
                const { lat, lon } = data[0];
                L.circle([parseFloat(lat), parseFloat(lon)], {
                  color: "red",
                  fillColor: "#f03",
                  fillOpacity: 0.5,
                  radius: 2000,
                })
                  .addTo(map)
                  .bindPopup(i.name);
              } else {
                console.log("No geodata found");
              }
            })
            .catch((error) => {
              console.log("Error trying to fetch data: ", error);
            });
        });
      }
    );
    return unsubscribe;
  });
</script>

<!-- HTML -->

<div id="map" style="z-index: 999;"></div>

<!-- stackoverflow.com/questions/2305669/all-about-z-index -->

<!-- Css -->
<style>
  #map {
    display: grid;
    flex: 1;
    grid-gap: 20px;
    padding: 20px;
    height: 400px;
    margin-left: 10px;
  }
</style>
