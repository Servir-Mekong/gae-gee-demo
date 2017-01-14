var map;

function initMap() {
    // Create a map object and specify the DOM element for display.
    map = new google.maps.Map(
        document.getElementById('map'),
        {
            zoom: 2,
            center: new google.maps.LatLng(2.8,-187.3),
            mapTypeId: 'terrain'
        }
    );
}

window.eqfeed_callback = function(results) {
    for (var i = 0; i < results.features.length; i++) {
        var coords = results.features[i].geometry.coordinates;
        var latLng = new google.maps.LatLng(coords[1],coords[0]);
        var marker = new google.maps.Marker({
            position: latLng,
            map: map
        });
    }
}

// {'features' :
//  [ {'geometry' : {'coordinates' : [coord1 coord2]}}
//    {'geometry' : {'coordinates' : [coord1 coord2]}}
//    {'geometry' : {'coordinates' : [coord1 coord2]}}
//    ...
//    {'geometry' : {'coordinates' : [coord1 coord2]}}
//  ]
// }
