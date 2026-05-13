const http = require("http");
const PORT = process.env.PORT || 4600;

const sendJson = (res, status, body) => {
  res.writeHead(status, { "Content-Type": "application/json" });
  res.end(JSON.stringify(body));
};

const server = http.createServer((req, res) => {
  const { method, url } = req;
  const [pathOnly] = url.split("?");

  // Repository inspection result:
  // No external HTTP dependencies were found in source/config files.
  // This local mock provides placeholder external endpoints for safe future wiring.

  // @endpoint GET /health
  if (method === "GET" && pathOnly === "/health") {
    return sendJson(res, 200, {
      status: "ok",
      mock: "external-endpoints-mock",
      note: "No external endpoints detected in repository scan"
    });
  }

  // @endpoint GET /external/status
  if (method === "GET" && pathOnly === "/external/status") {
    return sendJson(res, 200, {
      externalEndpointsDetected: false,
      scannedProject: "TrikiTrueke-BackEnd",
      message: "No outbound third-party HTTP endpoints were found in source or configuration files."
    });
  }

  sendJson(res, 404, {
    error: "Endpoint not defined",
    path: pathOnly,
    method
  });
});

server.listen(PORT, () => {
  console.log(`External endpoints mock server running on port ${PORT}`);
});
