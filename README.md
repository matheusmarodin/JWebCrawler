# JWebCrawler

This application receives an URL and collects all links within it. Then it can start doing it again recursively with the newly found links. 

**Web application available**: [JWebCrawler](https://jwebcrawler.mybluemix.net/)


## Before you begin

You'll need a [IBM Cloud account](https://console.ng.bluemix.net/registration/), [Git](https://git-scm.com/downloads), [Cloud Foundry CLI](https://github.com/cloudfoundry/cli#downloads), and [Maven](https://maven.apache.org/download.cgi) installed. If you use [IBM Cloud Private](https://www.ibm.com/cloud-computing/products/ibm-cloud-private/), you need access to the [IBM Cloud Private Cloud Foundry](https://www.ibm.com/support/knowledgecenter/en/SSBS6K_2.1.0/cloud_foundry/overview.html) environment.

## Instructions

**How to clone and deploy**: [README_cloud_foundry.md](README_cloud_foundry.md)

**How to setup a container**: [README_kubernetes.md](README_kubernetes.md)

## References

This application was developed using the following projects as starting point:

**Java App**: [get-started-java](https://github.com/IBM-Cloud/get-started-java)

**Crawler**: [crawler4j](https://github.com/yasserg/crawler4j)

