async function loadCampaigns() {
    const tableContainer = document.getElementById("campaign-table");
    const summaryContainer = document.getElementById("summary");
    const errorContainer = document.getElementById("error");

    tableContainer.innerHTML = "";
    summaryContainer.innerHTML = "";
    errorContainer.textContent = "";

    try {
        const response = await fetch("/api/campaigns");
        if (!response.ok) {
            throw new Error(`Failed to load campaigns (${response.status})`);
        }
        const campaigns = await response.json();

        if (!Array.isArray(campaigns) || campaigns.length === 0) {
            tableContainer.innerHTML = "<p>No campaigns found.</p>";
            return;
        }

        // Build summary stats
        const totals = campaigns.reduce((acc, c) => {
            acc.impressions += c.impressions;
            acc.clicks += c.clicks;
            acc.conversions += c.conversions;
            acc.cost += c.cost ? Number(c.cost) : 0;
            return acc;
        }, { impressions: 0, clicks: 0, conversions: 0, cost: 0 });

        const ctr = totals.impressions > 0 ? (totals.clicks * 100.0) / totals.impressions : 0;
        const convRate = totals.clicks > 0 ? (totals.conversions * 100.0) / totals.clicks : 0;

        summaryContainer.innerHTML = `
          <div class="summary-item">
            <h3>Total Impressions</h3>
            <p>${totals.impressions.toLocaleString()}</p>
          </div>
          <div class="summary-item">
            <h3>Total Clicks</h3>
            <p>${totals.clicks.toLocaleString()}</p>
          </div>
          <div class="summary-item">
            <h3>Total Conversions</h3>
            <p>${totals.conversions.toLocaleString()}</p>
          </div>
          <div class="summary-item">
            <h3>Total Cost</h3>
            <p>$${totals.cost.toFixed(2)}</p>
          </div>
          <div class="summary-item">
            <h3>CTR</h3>
            <p>${ctr.toFixed(2)}%</p>
          </div>
          <div class="summary-item">
            <h3>Conversion Rate</h3>
            <p>${convRate.toFixed(2)}%</p>
          </div>
        `;

        // Build table
        const rows = campaigns.map(c => {
            const status = (c.status || "").toLowerCase();
            const badgeClass =
                status === "active" ? "badge-active" :
                status === "planned" ? "badge-planned" :
                status === "paused" ? "badge-paused" : "badge-completed";

            return `
              <tr>
                <td>${c.id}</td>
                <td>${c.name}</td>
                <td>
                  <span class="badge ${badgeClass}">
                    ${c.status}
                  </span>
                </td>
                <td>${c.impressions.toLocaleString()}</td>
                <td>${c.clicks.toLocaleString()}</td>
                <td>${c.conversions.toLocaleString()}</td>
                <td>$${c.cost ? Number(c.cost).toFixed(2) : "0.00"}</td>
                <td>
                  <span class="pill">
                    ${c.startDate || ""} → ${c.endDate || ""}
                  </span>
                </td>
              </tr>
            `;
        }).join("");

        tableContainer.innerHTML = `
          <table>
            <thead>
              <tr>
                <th>ID</th>
                <th>Campaign</th>
                <th>Status</th>
                <th>Impr.</th>
                <th>Clicks</th>
                <th>Conv.</th>
                <th>Cost</th>
                <th>Dates</th>
              </tr>
            </thead>
            <tbody>
              ${rows}
            </tbody>
          </table>
          <p style="margin-top:1rem;font-size:0.8rem;color:#9ca3af;">
            API base URL: <code>/api/campaigns</code> &mdash;
            inspect requests in your browser dev tools.
          </p>
        `;
    } catch (err) {
        console.error(err);
        errorContainer.textContent = err.message || "Unexpected error loading campaigns.";
    }
}

document.addEventListener("DOMContentLoaded", loadCampaigns);
