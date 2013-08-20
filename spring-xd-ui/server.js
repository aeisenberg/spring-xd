/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

var port = 9889;

var http = require('http');
var fs = require('fs');
var path = require('path');
var mime = require('mime');

http.createServer(function(request, response) {

    var filePath, url = request.url;

    if (url.indexOf('/data') === 0) {
        // create random data
        console.log('creating random data for ' + url);
        var data = [];
        data.push(Math.floor(Math.random() * 1000));
        for (var i = 1; i < 2000; i++) {
            data.push(Math.round(((Math.random() * 1000) + data[i-1]) /2));
        }
        response.writeHead(200, { 'Content-Type': 'application/json' });
        response.end(JSON.stringify(data), 'utf-8');
        return;
    }

    if (url === '/' || url === '') {
        filePath = 'client/index.html';
    } else {
        filePath = 'client' + url;
    }

	var contentType = mime.lookup(filePath, 'text/plain');
	console.log("Serving " + filePath + " as " + contentType);

	path.exists(filePath, function(exists) {

		if (exists) {
			fs.readFile(filePath, function(error, content) {
				if (error) {
					response.writeHead(500);
					response.end();
				} else {
					response.writeHead(200, { 'Content-Type': contentType });
					response.end(content, 'utf-8');
				}
			});
		} else {
			response.writeHead(404);
			response.end();
		}
	});
}).listen(port);
console.log('Server running at ' + port);