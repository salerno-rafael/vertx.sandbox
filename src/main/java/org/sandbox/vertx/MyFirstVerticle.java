package org.sandbox.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;

public class MyFirstVerticle extends AbstractVerticle {

	public static void main(String[] args) throws Exception {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(MyFirstVerticle.class.getName());
	}

	@Override
	public void start(Future<Void> fut) {
		vertx.createHttpServer()
				.requestHandler(
						r -> {
							r.response().end(
									"<h1>Hello from my first "
											+ "Vert.x 3 application</h1>");
						}).listen(8080, result -> {
					if (result.succeeded()) {
						fut.complete();
					} else {
						fut.fail(result.cause());
					}
				});
	}
}
