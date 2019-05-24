# Getting started tutorial

By following the Liberty for Java getting started tutorial, you'll set up a development environment, deploy an app locally and on IBM Cloud™, and integrate a database service in your app.

Throughout these docs, references to the Cloud Foundry CLI are now updated to the IBM Cloud CLI! The IBM Cloud CLI has the same familiar Cloud Foundry commands, but with better integration with IBM Cloud accounts and other services. Learn more about getting started with the IBM Cloud CLI in this tutorial.
Before you begin

You'll need the following accounts and tools: [IBM Cloud account](https://cloud.ibm.com/registration), [IBM Cloud CLI](https://cloud.ibm.com/docs/cli/reference/ibmcloud/download_cli.html), [Git](https://git-scm.com/downloads) and [Maven](https://maven.apache.org/download.cgi).

## Step 1: Clone the sample app

First, clone the sample app GitHub repo.


```bash
git clone https://github.com/matheusmarodin/JWebCrawler
```

## Step 2: Run the app locally using command line

Use Maven to build your source code and run the resulting app.

On the command line, change the directory to where the sample app is located.

```bash
cd get-started-java
```

Use Maven to install dependencies and build the .war file.


```bash
mvn clean install
```

Run the app locally on Liberty.

```bash
mvn install liberty:run-server
```

When you see the message The server defaultServer is ready to run a smarter planet., you can view your app at: http://localhost:9080/.

To stop your app, press Ctrl-C in the command-line window where you started the app.

## Step 3: Prepare the app for deployment

To deploy to IBM Cloud, it can be helpful to set up a manifest.yml file. The manifest.yml includes basic information about your app, such as the name, how much memory to allocate for each instance and the route. We've provided a sample manifest.yml file in the get-started-java directory.


```
  applications:
   - name: GetStartedJava
     random-route: true
     path: target/GetStartedJava.war
     memory: 512M
     instances: 1
```
 
In this manifest.yml file, random-route: true generates a random route for your app to prevent your route from colliding with others. If you choose to, you can replace random-route: true with host: myChosenHostName, supplying a host name of your choice.

## Step 4: Deploy to IBM Cloud

You can use the IBM Cloud CLI to deploy apps.

Log in to your IBM Cloud account, and select an API endpoint.

```bash
ibmcloud login
```

If you have a federated user ID, instead use the following command to log in with your single sign-on ID. See Logging in with a federated ID to learn more.

```bash
ibmcloud login --sso
```

Target a Cloud Foundry org and space:

```bash
ibmcloud target --cf
```

If you don't have an org or a space set up, see Adding orgs and spaces.

From within the get-started-java directory, push your application to IBM Cloud.

```bash
ibmcloud cf push
```

Deploying your application can take a few minutes. When deployment completes, a message shows that your app is running. View your app at the URL listed in the output of the push command with "/GetStartedJava" appended to the end, or view both the app deployment status and the URL by running the following command:

```bash
ibmcloud cf apps
```

## Step 5: Add a database

Next, we'll add an IBM Cloudant NoSQL database to this application and set up the application so that it can run locally and on IBM Cloud.

In your browser, log in to IBM Cloud and go to the Dashboard. Select Create resource.

Search for IBM Cloudant, and select the service.

For Available authentication methods, select Use both legacy credentials and IAM. You can leave the default settings for the other fields.

Click Create to create the service.

In the navigation, go to Connections, then click Create connection. Select your application, and click Connect.

Using the default values, click Connect & restage app to connect the database to your application. Click Restage when prompted.

IBM Cloud will restart your application and provide the database credentials to your application using the VCAP_SERVICES environment variable. This environment variable is available to the application only when it is running on IBM Cloud.

Environment variables enable you to separate deployment settings from your source code. For example, instead of hardcoding a database password, you can store it in an environment variable that you reference in your source code.

## Step 6: Use the database

We're now going to update your local code to point to this database. We'll store the credentials for the services in a properties file. This file will get used ONLY when the application is running locally. When running in IBM Cloud, the credentials will be read from the VCAP_SERVICES environment variable.

Find your app in the IBM Cloud Resource List. On the Service Details page for your app, click Connections in the sidebar. Click the IBM Cloudant menu icon (…) and select View credentials.

Copy and paste just the url from the credentials to the url field of the src/main/resources/cloudant.properties file, and save the changes.

```bash
cloudant_url=https://123456789 ... bluemix.cloudant.com
```
Restart the server.

Refresh your browser view at http://localhost:9080/.

Your local app and the IBM Cloud app are sharing the database.
