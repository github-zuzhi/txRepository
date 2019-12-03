define([], function() {
	var webSocket = {
		appPath : getPath("app"),
		init : function() {
			webSocket.svc_connectPlatform("test");
		},
		
	}
	function svc_connectPlatform(topSid) {
		var wsServer = 'ws://127.0.0.1:4444/' + topSid;
		try {
			svc_websocket = new WebSocket(wsServer);
		} catch (evt) {
			console.log("new WebSocket error:" + evt.data);
			svc_websocket = null;
			if (typeof (connCb) != "undefined" && connCb != null)
				connCb("-1", "connect error!");
			return;
		}
		svc_websocket.onopen = svc_onOpen;
		svc_websocket.onclose = svc_onClose;
		svc_websocket.onmessage = svc_onMessage;
		svc_websocket.onerror = svc_onError;
	}

	function svc_onOpen(evt) {
		console.log("Connected to WebSocket server.");
	}

	function svc_onClose(evt) {
		console.log("Disconnected");
	}

	function svc_onMessage(evt) {
		console.log('Retrieved data from server: ' + evt.data);
	}

	function svc_onError(evt) {
		console.log('Error occured: ' + evt.data);
	}

	function svc_send(msg) {
		if (svc_websocket.readyState == WebSocket.OPEN) {
			svc_websocket.send(msg);
		} else {
			console.log("send failed. websocket not open. please check.");
		}
	}
	return webSocket;
});
