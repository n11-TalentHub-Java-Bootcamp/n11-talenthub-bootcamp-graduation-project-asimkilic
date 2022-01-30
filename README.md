# n11-talenthub-bootcamp-graduation-project

<div id="top"></div>

<!-- PROJECT LOGO -->
<br />
<div align="center">
  <h3 align="center"> n11 TalentHub Java Bootcamp</h3>
<h4 align="center">Graduation Project</h4>

  <p align="center">
    ·
    <a href="https://github.com/n11-TalentHub-Java-Bootcamp/n11-talenthub-bootcamp-graduation-project-asimkilic/issues">Report Bug</a>
    ·
    <a href="https://github.com/n11-TalentHub-Java-Bootcamp/n11-talenthub-bootcamp-graduation-project-asimkilic/issues">Request Feature</a>
  </p>


</div>



<!-- TABLE OF CONTENTS -->

<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#previews">Previews</a></li>
  </ol>
</details>




<!-- ABOUT THE PROJECT -->
## About The Project

A small credit system was done.
* Users can register with their Republic of Turkey credentials. 
* Credential authentication is provided via KpsPublic. (It can be turned off and on via application.properties.) 
* Credit scores are learned after users' verification processes, and their loan applications are finalized. 
* Credit results are sent to the user as sms.
* A user cannot receive two credits at the same time. 
* A special "requestid" is assigned to each request that comes to the system. And in case of an error, this information is returned to the user. When the user provides the system authorities with this Id, the error consisting of logs can be detected. 
* Swagger documentation has been prepared. 

<p align="right">(<a href="#top">back to top</a>)</p>



### Built With

- Java 11
- Spring Boot
- PostgreSQL
- ReactJS


<p align="right">(<a href="#top">back to top</a>)</p>



<!-- GETTING STARTED -->

## Getting Started


### Prerequisites

* Docker Desktop
* Java 11 or newer
* Node JS


### Installation

1. Clone the repo
   ```sh
   https://github.com/n11-TalentHub-Java-Bootcamp/n11-talenthub-bootcamp-graduation-project-asimkilic.git
   ```
2. Configure application.settings files for PostgreSql connection, Turkish Republic Id no verification and Credit Score services.

<br />



3. Run Docker-Compose file for PostgreSQL.

   ```powershell
    > docker-compose -f docker-compose.yml up -d
   ```

   

   

4. Go to http://localhost:5431 and select PostgreSQL 
   username: postgres
   password: 12345  ( you can change password in docker-compose.yml)
   and create database named loan_application
   (If you do not want to use PostgreSQL on docker please configure database connection in application.properties)

5. Run the project with IDE or Dockerfile.

   ```powershell
   > docker build -t loan-application .
   > docker run -d 8080:8080 loan-application 
   ```

   (Note: When the port number is changed, proxy settings must be made in loan-application-react/package.json. )

6.  You can find the documentation of api on http://localhost:8080/v3/api-docs/asimkilic-n11

7. In React  project 

   ```powershell
   # install dependencies with npm
   npm install
   # or install dependencies with yarn
   yarn
   # serve at localhost:3000
   npm start
   
   ```

   

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/NewFeatures`)
3. Commit your Changes (`git commit -m 'Added some Features'`)
4. Push to the Branch (`git push origin feature/NewFeatures`)
5. Open a Pull Request

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE.txt` for more information.

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- CONTACT -->

## Contact

Abdullah Asım KILIÇ - a.asim.kilic@gmail.com

Project Link: [https://github.com/n11-TalentHub-Java-Bootcamp/n11-talenthub-bootcamp-graduation-project-asimkilic](https://github.com/n11-TalentHub-Java-Bootcamp/n11-talenthub-bootcamp-graduation-project-asimkilic)

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- PREVIEWS -->

## Previews


* 

  

<p align="right">(<a href="#top">back to top</a>)</p>