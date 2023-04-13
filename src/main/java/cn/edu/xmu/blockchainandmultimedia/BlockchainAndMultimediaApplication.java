package cn.edu.xmu.blockchainandmultimedia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//@SpringBootApplication(scanBasePackages = {"cn.edu.xmu.javaee.core",
//        "cn.edu.xmu.oomall.order"})
//@EnableDiscoveryClient
public class BlockchainAndMultimediaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlockchainAndMultimediaApplication.class, args);
    }

}
