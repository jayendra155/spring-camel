package com.springcamel.test;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringDelegatingTestContextLoader;
import org.apache.camel.test.spring.CamelSpringJUnit4ClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.apache.camel.spring.javaconfig.SingleRouteCamelConfiguration;

@RunWith(CamelSpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { FilterTest.ContextConfig.class }, loader = CamelSpringDelegatingTestContextLoader.class)
public class FilterTest {
	@EndpointInject(uri = "mock:result")
	protected MockEndpoint resultEndpoint;

	@Produce(uri = "direct:start")
	protected ProducerTemplate template;

	@DirtiesContext
	@Test
	public void testSendMatchingMessage() throws Exception {
		String expectedBody = "<matched/>";

		resultEndpoint.expectedBodiesReceived(expectedBody);
		template.sendBodyAndHeader(expectedBody, "foo", "bar");

		resultEndpoint.assertIsSatisfied();
	}

	@DirtiesContext
	@Test
	public void testSendNotMatchingMessage() throws Exception {
		resultEndpoint.expectedMessageCount(0);

		template.sendBodyAndHeader("<notMatched/>", "foo",
				"notMatchedHeaderValue");

		resultEndpoint.assertIsSatisfied();
	}

	@Configuration
	public static class ContextConfig extends SingleRouteCamelConfiguration {
		@Bean
		public RouteBuilder route() {
			return new RouteBuilder() {
				public void configure() {
					from("direct:start").filter(header("foo").isEqualTo("bar"))
							.to("mock:result");
				}
			};
		}
	}
}
