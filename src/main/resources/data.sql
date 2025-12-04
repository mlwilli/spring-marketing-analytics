INSERT INTO campaigns
    (name, description, start_date, end_date, budget, status,
     impressions, clicks, conversions, cost, created_at, updated_at)
VALUES
    ('Spring Launch 2025',
     'Digital campaign for the spring product launch.',
     '2025-03-01', '2025-03-31',
     50000.00, 'ACTIVE',
     250000, 12000, 800, 42000.00,
     '2025-02-15 09:00:00', '2025-02-15 09:00:00'),

    ('Holiday Retargeting',
     'Retargeting previous customers ahead of the holiday season.',
     '2025-11-01', '2025-12-31',
     80000.00, 'PLANNED',
     0, 0, 0, 0.00,
     '2025-08-01 10:00:00', '2025-08-01 10:00:00'),

    ('Brand Awareness Q2',
     'Always-on brand awareness across social and search.',
     '2025-04-01', '2025-06-30',
     60000.00, 'COMPLETED',
     500000, 22000, 1500, 58000.00,
     '2025-01-10 08:30:00', '2025-07-01 08:30:00');
