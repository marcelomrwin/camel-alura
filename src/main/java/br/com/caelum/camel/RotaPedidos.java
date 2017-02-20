package br.com.caelum.camel;

import java.util.concurrent.TimeUnit;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class RotaPedidos {

	public static void main(String[] args) throws Exception {

		CamelContext context = new DefaultCamelContext();
		context.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {
				from("file:pedidos?delay=5s&noop=true").split().xpath("/pedido/itens/item").filter()
						.xpath("/item/formato[text()='EBOOK']").marshal().xmljson().log("${id} \n ${body}")
						.setHeader(Exchange.FILE_NAME, simple("${file:name.noext}-${header.CamelSplitIndex}.json")).to("file:saida");
			}

		});
		context.start();
		TimeUnit.SECONDS.sleep(20);
	}
}
