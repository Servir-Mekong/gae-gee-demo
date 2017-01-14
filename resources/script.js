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

var eeMapType;

function showEEMap(eeMapId, eeToken) {
    console.log("EE Map ID: " + eeMapId);
    console.log("EE Token: " + eeToken);

    eeMapType = new google.maps.ImageMapType({
        'name': 'ecomap',
        'opacity': 1.0,
        'tileSize': new google.maps.Size(256,256),
        'getTileUrl': function(tile,zoom) {
            return 'https://earthengine.googleapis.com/map/'
                + eeMapId + '/' + zoom + '/' + tile.x + '/' + tile.y
                + '?token=' + eeToken;
        }
    });

    console.log("EE Map Type:" + eeMapType);
    
    // map.overlayMapTypes.push(eeMapType);
}
