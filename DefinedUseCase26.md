# USE CASE: Produce a Report on the top N populated countries in a continent where N is provided by the user.

## CHARACTERISTIC INFORMATION

### Goal in Context

I want to complete a report on the number people who speak the following languages, Chinese, English, Hindi, Spanish and Arabic from the greatest number of speakers to smallest including percentage of the world population that speaks these languages in order to allow the organisation gain insight on global population of speakers these languages have.

### Scope

Organisation.

### Level

Primary task.

### Preconditions

Data on countries will be available.

### Success End Condition

A report listing top N populated countries provided user

### Failed End Condition

No report is produced.

### Primary Actor

Organisation.

### Trigger

A request for demographic information is sent to the Organisation.

## MAIN SUCCESS SCENARIO

1. Organisation requests data listing on the number people who speak the following languages, Chinese, English, Hindi, Spanish and Arabic from the greatest number of speakers to smallest including percentage of the world population that speaks these languages
2. The reporting system requests report parameters, such as columns to be filled in.
3. The following columns are chosen by the organization: code, name, contents, region, population, and capital.
4. Data about countries is retrieved by the reporting system from the database.
5. The report is generated using the specified columns by the reporting system.
6. The report is made available for analysis
## EXTENSIONS

3. **Data unavailable**:
    1. Should any country-specific data be incomplete or unavailable, the organisation is notified by the reporting system.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0