<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>About Prov</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="../bootstrap/css/bootstrap.css" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 60px;
        padding-bottom: 40px;
      }
      .sidebar-nav {
        padding: 9px 0;
      }
    </style>
    <script src="../jquery/jquery-1.7.2.min.js"></script>
    <link href="../bootstrap/css/bootstrap-responsive.css" rel="stylesheet">

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <%@ include file="google-analytics.jsp" %> 
  </head>

  <body>

    <%@ include file="header.jsp" %> 
<script>
    $('#header-about').addClass("active");
</script>    


    <section class="round">
      <div class="span12 row-fluid">
	<div class="span8">
	  <p>Provenance is defined as a record that describes the people,
	    institutions, entities, and activities involved in producing,
	    influencing, or delivering a piece of data or a thing.
	    In particular, the provenance of information is crucial in deciding
	    whether information is to be trusted, how it should be integrated with
	    other diverse information sources, and how to give credit to its
	    originators when reusing it.  In an open and inclusive environment
	    such as the Web, where users find information that is often contradictory or
	    questionable, provenance can help those users to make trust judgements.</p>
	  
	  <p>The PROV Family of Specifications is a set  of specifications defined by the <a href="http://www.w3.org">W3C PROV Working Group</a> defining various aspects that are necessary to achieve the vision of inter-operable interchange of provenance information in heterogeneous environments such as the Web.  The specifications are:<p>
	    <ul>
	      <li> <a href="http://www.w3.org/TR/prov-overview/">PROV-OVERVIEW</a>, an overview of the family of  PROV specifications;</li>
	      <li> <a href="http://www.w3.org/TR/prov-primer/">PROV-PRIMER</a>, a primer for the PROV data model;</li>
	      <li> <a href="http://www.w3.org/TR/prov-dm/">PROV-DM</a>, the PROV data model for provenance;</li>
	      <li> <a href="http://www.w3.org/TR/prov-constraints/">PROV-CONSTRAINTS</a>, a set of constraints applying to the PROV data model;</li>
	      <li> <a href="http://www.w3.org/TR/prov-n/">PROV-N</a>, a notation for provenance aimed at human consumption;</li>
	      <li> <a href="http://www.w3.org/TR/prov-o/">PROV-O</a>, the PROV ontology, an OWL2 ontology allowing the mapping of PROV to RDF;</li>
	      <li> <a href="http://www.w3.org/TR/prov-aq/">PROV-AQ</a>, the mechanisms for accessing and querying provenance; </li>
	      <li> <a href="http://www.w3.org/TR/prov-xml/">PROV-XML</a>, an XML schema for the PROV data model;</li>
	      <li> <a href="http://www.w3.org/TR/prov-links/">PROV-LINKS</a>, a mechanism for linking entities across bundles;</li>
	      <li> <a href="http://www.w3.org/TR/prov-dictionary/">PROV-DICTIONARY</a>, tackling the provenance of dictionaries.</li>
	    </ul>

	  <p>ProvValidator, ProvTranslator and ProvExpander services use <a href="http://www.github.com/ProvToolbox">ProvToolbox</a> a Java toolkit to create, save, and read PROV expressions. </p>

<ul>

	  <li> <b>ProvTranslator</b> reads the PROV representation submitted by the user, builds an in memory representation using Java objects and then saves it to the requested represententation. Conversion from any PROV representation to Java objects is performed by the ProvToolbox; likewise, conversion from Java objects to other representations is also performed by the Prov Toolbox.</lip>

          <li><b>ProvValidator</b> performs a series of test to check validity of an in-memory PROV representation. It returns a validation report listing issues identified by the validator.</li>

	  <li><b>ProvExpander</b> expands a provenance template using a series of bindings; it returns a PROV document.</li>
</ul>	  

<p> ProvValidator and ProvTranslator can be used either interactivity from a web browser, or as REST services, according to the <a href="api"><b>following API</b></a>.</p>

	</div>


      </div>


      
    </section>


<%@ include file="footer.jsp" %> 

  </body>
</html>
