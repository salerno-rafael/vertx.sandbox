package org.sandbox.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

public class MySecondVerticle extends AbstractVerticle {

	public static void main(String[] args) throws Exception {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(MySecondVerticle.class.getName());
	}

	@Override
	public void start(Future<Void> fut) {
	 Router router = Router.router(vertx);

	 router.route("/").handler(routingContext -> {
	   HttpServerResponse response = routingContext.response();
	   response
	       .putHeader("content-type", "text/html")
	       .end("<h1>Hello from my first Vert.x 3 application</h1>");
	 });
	 
	 router.route("/test").handler(routingContext -> {
		   HttpServerResponse response = routingContext.response();
		   response
		       .putHeader("content-type", "text/html")
		       .end("<h1>This route is just for test</h1>");
		 });

	 vertx
	     .createHttpServer()
	     .requestHandler(router::accept)
	     .listen(
	         config().getInteger("http.port", 8080),
	         result -> {
	           if (result.succeeded()) {
	             fut.complete();
	           } else {
	             fut.fail(result.cause());
	           }
	         }
	     );
	}
}
