<script>
  import { onMount } from "svelte";
  import {
    companyJobApplications,
    fetchJobApplicationsForCompany,
    getCvForUser,
    companyJobListings,
    fetchCompanyJobListings,
  } from "./db.js";
  import { writable } from "svelte/store";
  import Button from "./Button.svelte";

  const selectedJobListing = writable(null);

  onMount(() => {
    fetchCompanyJobListings();
    fetchJobApplicationsForCompany();
  });

  function toggleItem(jobListingId) {
    selectedJobListing.update((current) =>
      current === jobListingId ? null : jobListingId
    );
  }

  async function openCv(userId) {
    const cvUrl = await getCvForUser(userId);
    if (cvUrl) {
      window.open(cvUrl, "_blank");
    }
  }
</script>

{#if $companyJobListings}
  <table>
    <thead>
      <tr>
        <th>Title</th>
        <th>Description</th>
        <th>Company</th>
        <th>Tech stack</th>
        <th>City</th>
        <th>Date published</th>
      </tr>
    </thead>
    <tbody>
      {#each $companyJobListings as jobListing}
        <tr on:click={() => toggleItem(jobListing.id)}>
          <td>{jobListing.name}</td>
          <td>{jobListing.description}</td>
          <td>{jobListing.companyName}</td>
          <td>{jobListing.programmingLanguages.join(", ")}</td>
          <td>{jobListing.city}</td>
          <td
            >{new Date(jobListing.createdDate).toLocaleString("en-GB", {
              day: "2-digit",
              month: "2-digit",
              year: "numeric",
              hour: "2-digit",
              minute: "2-digit",
              hour12: false,
            })}</td
          >
        </tr>
        {#if $selectedJobListing === jobListing.id}
          {#each $companyJobApplications.filter((app) => app.jobListing.id === jobListing.id) as application}
            <tr class="applicant">
              <td colspan="9">
                <div style="padding-left: 20px;">
                  <strong
                    >{application.user.firstName}
                    {application.user.lastName}</strong
                  > <br />
                  <strong>Email:</strong>
                  {application.user.email} <br />
                  <strong>Application Date:</strong>
                  {new Date(application.applicationDate).toLocaleString(
                    "en-GB",
                    {
                      day: "2-digit",
                      month: "2-digit",
                      year: "numeric",
                      hour: "2-digit",
                      minute: "2-digit",
                      hour12: false,
                    }
                  )}
                  <Button on:click={() => openCv(application.user.id)}
                    >View {application.user.firstName}'s CV</Button
                  >
                </div>
              </td>
            </tr>
          {/each}
        {/if}
      {/each}
    </tbody>
  </table>
{:else}
  <p>No job listings available</p>
{/if}

<style>
  table {
    width: 100%;
    border-collapse: collapse;
  }

  th,
  td {
    padding: 8px;
    text-align: left;
    border-bottom: 1px solid #ddd;
  }

  th {
    text-align: center;
    border-bottom: 1px solid #ddd;
    background-color: #201919;
    color: white;
  }

  tr:hover {
    background-color: #337e3a;
    color: white;
    cursor: pointer;
  }

  tr.applicant:hover {
    background-color: #575757;
    color: white;
  }
</style>
