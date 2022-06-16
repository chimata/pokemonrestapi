function showPokemonNamesWithImages() {

        var xHR = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject('Microsoft.XMLHTTP');

        function reportStatus() {
            if (xHR.readyState == 4)               // Request completed.
                showTheList(this.responseText);
        }
        xHR.onreadystatechange = reportStatus;
        xHR.open("GET", "http://localhost:9009/api/v1/images", true);   // true = asynchronous request
        xHR.send();

        function showTheList(json) {
            var arrItems = [];
            arrItems = JSON.parse(json); 	// Populate array with JSON data.

            var div = document.getElementById('container');     // The parent <div>.
            div.innerHTML = '';

            // Loop through data in the JSON array.
            for (i = 0; i <= arrItems.length - 1; i++) {

                var divLeft = document.createElement('div');
                divLeft.innerHTML = arrItems[i].imageName;

                var img = document.createElement('img');
                img.src = arrItems[i].imagePath;

                var divRight = document.createElement('div');
                divRight.setAttribute('style', 'border-left: solid 3px #ddd;');
                divRight.appendChild(img);

               // Add the child DIVs to parent DIV.
                div.appendChild(divLeft);
                div.appendChild(divRight);
            }
        }
    }