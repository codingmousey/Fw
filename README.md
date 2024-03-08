# Fw (Final Work)

github repo for my final work junior job

# Backend API Endpoints

## Registration

- **URL**: http://localhost:6969/api/register
- **Description**: registering a new user

## Login

- **URL**: http://localhost:6969/api/login
- **Description**: login in a user

## Job Listings

- **URL**: http://localhost:6969/api/joblistings
- **Description**: get all job listings

- **URL**: http://localhost:6969/api/langById/{id}
- **Description**:get all programming languges from a certain company

- **URL**: http://localhost:6969/api/add_joblisting
- **Description**: add a new job listing

- **URL**: http://localhost:6969/api/add_joblisting_bot
- **Description**: add a new job listing from python script

## Prefs

- **URL**: http://localhost:6969/api/prefs
- **Description**: get all prefs from a user

- **URL**: http://localhost:6969/api/prefs
- **Description**: add a new pref for a user

- **URL**: http://localhost:6969/api/prefs
- **Description**: delete pref from a user

## User CV

- **URL**: http://localhost:6969/api/uploadCv
- **Description**: uploading a users cv

- **URL**: http://localhost:6969/api/getCv
- **Description**: sending back a users cv

## Job Applications

- **URL**: http://localhost:6969/api/apply
- **Description**: adding a job application

- **URL**: http://localhost:6969/api/getUserJobApplications
- **Description**: getting job applications for a user

- **URL**: http://localhost:6969/api/removeApplication
- **Description**: deleting a job application by id

## Company Specific

- **URL**: http://localhost:6969/api/joblistingsFromCompany
- **Description**: getting all joblistings from a company

- **URL**: http://localhost:6969/api/jobApplicationsForCompany
- **Description**: getting all job applications for a company
