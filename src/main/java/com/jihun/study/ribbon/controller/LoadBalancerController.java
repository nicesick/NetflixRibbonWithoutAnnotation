package com.jihun.study.ribbon.controller;

import com.jihun.study.ribbon.utils.Utils;
import com.netflix.client.ClientFactory;
import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import com.netflix.config.ConfigurationManager;
import com.netflix.discovery.EurekaClient;
import com.netflix.loadbalancer.LoadBalancerBuilder;
import com.netflix.niws.client.http.RestClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class LoadBalancerController {
//    private  loadBalancer;
    private RestClient client;

    LoadBalancerController() throws IOException {
//        List<Server> serverList = new ArrayList<>();
//        serverList.add(new Server("www.google.com"  ,80));
//        serverList.add(new Server("www.yahoo.com"   ,80));
//        serverList.add(new Server("www.github.com"  ,80));
//
//        loadBalancer = LoadBalancerBuilder
//                            .newBuilder()
//                            .buildFixedServerListLoadBalancer(serverList);

        ConfigurationManager.loadPropertiesFromResources("application.properties");
        System.out.println(ConfigurationManager.getConfigInstance().getProperty("sample-client.ribbon.listOfServers"));

        client = (RestClient) ClientFactory.getNamedClient("sample-client");
    }

//    @RequestMapping("loadBalance/sync")
//    public ModelAndView getServerNotREST() {
//        HttpRequest     request                         ;
//        HttpResponse    response                        ;
//        ModelAndView    result      = new ModelAndView();
//
//        try {
//            request = HttpRequest.newBuilder().uri("/").build();
//            response = client.executeWithLoadBalancer(request);
//
//            System.out.println("Status code for " + response.getRequestedURI() + "  :" + response.getStatus());
//
//            result.setView(new View() {
//                @Override
//                public void render(Map<String, ?> map, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
//                    httpServletResponse = ;
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return result;
//    }

    @RequestMapping("loadBalance/async")
    @ResponseBody
    public String getServerREST() {
        HttpRequest     request         ;
        HttpResponse    response        ;
        String          result      = "";

        try {
            request = HttpRequest.newBuilder().uri("/").build();
            response = client.executeWithLoadBalancer(request);

            System.out.println("Status code for " + response.getRequestedURI() + "  :" + response.getStatus());
            result = Utils.parseResponse(response.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

//        Server server = loadBalancer.chooseServer();
//
//        System.out.println("server = " + server);

//        HttpResourceGroup httpResourceGroup =
//                Ribbon.createHttpResourceGroupBuilder("loadBalance")
//                .withClientOptions(ClientOptions
//                        .create()
//                        .withMaxAutoRetries(1)
//                        .withMaxAutoRetriesNextServer(3)
//                        .withConfigurationBasedServerList("www.yahoo.com:80,www.google.com:80,www.github.com:80")
//                )
//                .build();
//
//        HttpRequestTemplate<ByteBuf> requestTemplate = httpResourceGroup
//                .newTemplateBuilder("loadBalance", ByteBuf.class)
//                .withMethod("GET")
//                .withUriTemplate("/")
//                .build();
//
//        RibbonRequest<ByteBuf> request = requestTemplate.requestBuilder().build();
//        Observable<ByteBuf> result = request.observe();
//
//        System.out.println("result = " + result);
//
//        return "";
    }
}
